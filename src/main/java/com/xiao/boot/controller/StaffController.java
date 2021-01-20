package com.xiao.boot.controller;

import com.xiao.boot.bean.Staff;
import com.xiao.boot.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("jobId") String jobId,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        Staff staff = staffService.login(Integer.valueOf(jobId),password);
        if (staff == null){
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }
        model.addAttribute("msg","登录成功");
        session.setAttribute("staff",staff);
        return "index";
    }

    @GetMapping("/reset_code_page")
    public String resetCodePage(){
        return "staff/resetCode";
    }

    // 按工号查询
    @ResponseBody
    @GetMapping("/find_staff_by_id")
    public Staff findStaffById(@RequestParam("jobId") String jobId){
        Staff staff = staffService.findStaffById(Integer.valueOf(jobId));
        return staff;
    }

    @GetMapping("/reset_code")
    public String resetCode(@RequestParam("jobId") String jobId,Model model){
        staffService.resetCode(Integer.parseInt(jobId));
        model.addAttribute("msg","密码重置成功");
        return "staff/resetCode";
    }
    //返回添加员工页面
    @GetMapping("/add_staff_page")
    public String addStaffPage(){
        return "staff/addStaff";
    }
    // 添加员工
    @PostMapping("/add_staff")
    public String addStaff(Staff staff,Model model){
        staff.setRole(3);
        Integer row = staffService.addStaff(staff);
        if (row == 1){
           model.addAttribute("msg","添加成功");
        }
        return "staff/addStaff";
    }

    // 返回所有员工信息页面
    @GetMapping("/all_staff_page")
    public String findAllStaff(Model model){
        List<Staff> allStaff = staffService.findAllStaff();
        if (allStaff.size() == 0){
            model.addAttribute("msg","暂无员工信息");
            return "index";
        }
        model.addAttribute("allStaff",allStaff);
        return "staff/staff-list";
    }

    // 返回修改页面
    @GetMapping("/update_staff_page")
    public String update_staff_page(@RequestParam("id") String jobId,Model model){
        Staff staff = staffService.findStaffById(Integer.parseInt(jobId));
        model.addAttribute("staff",staff);
        return "staff/updateStaffPage";
    }

    // 修改员工信息
    @PostMapping("/update_staff")
    public String updateStaff(Staff staff,Model model){
        staff.setRole(null);
        staff.setDepartmentId(null);
        Integer row = staffService.updateStaff(staff);
//        if (row == 1){
//            model.addAttribute("修改","修改成功");
//        }
        return "redirect:/all_staff_page";
    }
    // 删除员工
    @GetMapping("/delete_staff")
    public String update_staff_page(@RequestParam("id") String jobId){
        Integer row = staffService.deleteStaffById(Integer.parseInt(jobId));
        return "redirect:/all_staff_page";
    }
    // 角色管理页面
    @GetMapping("/role_manage_page")
    public String role_manage_page(Model model){
        List<Staff> allStaff = staffService.findAllStaff();
        if (allStaff.size() == 0){
            model.addAttribute("msg","暂无员工信息");
            return "index";
        }
        model.addAttribute("allStaff",allStaff);
        return "staff/role_manage_page";
    }
    // 修改角色
    @GetMapping("/update_staff_role")
    public String update_staff_role(@RequestParam("jobId") String jobId,
                                    @RequestParam("role") String role){
        Staff staff = staffService.findStaffById(Integer.parseInt(jobId));
        Integer roleId = Integer.parseInt(role.split("/?")[0]);
        if (roleId == 2){
            Staff manager = staffService.findManagerByDep(staff.getDepartmentId());
            if (manager != null) {
                return "redirect:/role_manage_page";
            }
        }
        staff.setRole(roleId);
        staffService.updateStaff(staff);
        return "redirect:/role_manage_page";
    }

    // 查看个人信息
    @GetMapping("/self_info_page")
    public String self_info_page(HttpSession session,Model model){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = staffService.findStaffById(jobId);
        model.addAttribute("staff",staff);
        return "staff/self_info_page";
    }

    // 修改个人信息页面
    @GetMapping("/update_selfInfo_page")
    public String update_selfInfo_page(HttpSession session,Model model){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = staffService.findStaffById(jobId);
        model.addAttribute("staff",staff);
        return "staff/update_selfInfo_page";
    }

    // 修改个人信息
    @PostMapping("/update_selfInfo")
    public String update_selfInfo(Staff staff,Model model){
        staff.setRole(null);
        staff.setDepartmentId(null);
        staffService.updateStaff(staff);
        model.addAttribute("msg","修改成功");

        return "redirect:/self_info_page";
    }


    // 修改个人密码页面
    @GetMapping("/update_code_page")
    public String update_code_page(){
        return "staff/update_code_page";
    }
    // 根据jobId 和 password来查找员工
    @ResponseBody
    @GetMapping("/check_code")
    public Staff findStaffByLoginElement(@RequestParam("oldPassword")String oldPassword,
                                          HttpSession session){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = staffService.login(jobId,oldPassword);
        return staff;
    }

    @PostMapping("/update_code")
    public String updateCode(@RequestParam("newPassword")String newPassword,
                                         HttpSession session,
                                         Model model){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = new Staff(jobId,null,null,null,null,null,newPassword);
        staffService.updateStaff(staff);
        model.addAttribute("msg","修改成功");
        return "redirect:/welcome";
    }
    // 考勤页面
    @GetMapping("/kaoqing")
    public String kaoQing(){
        return "attendance/kaoqing";
    }

    @GetMapping("/find_dep_staff")
    public String findStaffByDep(HttpSession session,Model model){
        String departmentId = ((Staff) session.getAttribute("staff")).getDepartmentId();
        List<Staff> allStaff = staffService.findStaffByDep(departmentId);
        if (allStaff.size() == 0){
            model.addAttribute("msg","部门暂无员工记录");
            return "welcome";
        }
        model.addAttribute("allStaff",allStaff);
        return "staff/depStaff-list";
    }

    @GetMapping("/diaogang_page")
    public String findStaffByDep(Model model,@RequestParam("id") String id){
        Integer jobId = Integer.parseInt(id);
        Staff staff = staffService.findStaffById(jobId);
        model.addAttribute("staff",staff);
        return "staff/diaogang";
    }

    @PostMapping("/diaogang")
    public String diaoGang(Staff staff){
        staffService.updateStaff(staff);
        return "redirect:/find_dep_staff";
    }
}
