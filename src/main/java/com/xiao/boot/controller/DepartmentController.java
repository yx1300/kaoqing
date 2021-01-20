package com.xiao.boot.controller;

import com.xiao.boot.bean.Department;
import com.xiao.boot.bean.Staff;
import com.xiao.boot.service.DepartmentService;
import com.xiao.boot.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    StaffService staffService;
    @GetMapping("/index.html")
    public String index_page(){
        return "index";
    }

    @GetMapping("/add_dep_page")
    public String add_dep_age(){
        return "department/addDepartment";
    }

    @GetMapping("/welcome")
    public String add_dep_agea(){
        return "welcome";
    }
    // 添加部门信息
    @PostMapping("/add_dep")
    public String addDepartment(Department department,
                                Model model){
        departmentService.addDepartment(department);
        model.addAttribute("msg","添加部门成功");
        return "department/addDepartment";
    }
    // 查询所有部门信息
    @GetMapping("/find_all_dep_page")
    public String find_all_dep_page(Model model){
        List<Department> departments = departmentService.findAllDepartment();
        model.addAttribute("departments",departments);
//        if ( msg!= null && !msg.equals("")){
//            model.addAttribute("msg",msg);
//        }
        return "department/department-list";
    }
    // 返回修改页面
    @GetMapping("/update_dep_page")
    public String update_dep_page(@RequestParam("id") String id, Model model){
        Department department = departmentService.findDepartmentById(id);
        model.addAttribute("department",department);
        return "department/updateDepartment";
    }

    // 按部门名字查询

    @ResponseBody // 返回json数据格式
    @GetMapping("/findDepByName")
    public Department findDepByName(@RequestParam("name") String name){
        Department temp = departmentService.findDepartmentByName(name);
        return temp;
    }
    // 修改
    @PostMapping("/update_dep")
    public String updateDepartment(Department department,RedirectAttributes ra){
        departmentService.updateDepartment(department);
        ra.addAttribute("msg","修改成功");
        return "redirect:/find_all_dep_page";
    }
    // 删除部门

    @GetMapping("/delete_dep")
    public String deleteDepartment(@RequestParam("id") String id,RedirectAttributes ra){
        List<Staff> staffs = staffService.findAllStaffByDepartmentId(id);
        if (staffs.size() == 0){
            departmentService.deleteDepartmentById(id);
            ra.addAttribute("msg","删除成功");
            return "redirect:/find_all_dep_page";
        }else {
            ra.addAttribute("msg","无法部门存在员工,无法删除");
            return "redirect:/find_all_dep_page";
        }
    }
}
