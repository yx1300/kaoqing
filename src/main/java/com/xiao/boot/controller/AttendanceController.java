package com.xiao.boot.controller;

import com.xiao.boot.bean.Attendance;
import com.xiao.boot.bean.Staff;
import com.xiao.boot.service.AttendanceService;
import com.xiao.boot.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    StaffService staffService;
    // 上下班打卡
    @GetMapping("/daka")
    public String start(HttpSession session, @RequestParam("type") String t){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = staffService.findStaffById(jobId);
        Integer type = Integer.parseInt(t);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        Attendance attendance = new Attendance(null,time,jobId,staff.getName(),staff.getSex(),staff.getBirthday(),staff.getDepartmentId(),type);
        attendanceService.daKa(attendance);
        return "attendance/kaoqing";
    }

    // 查看打卡记录
    @GetMapping("/find_daka_record")
    public String findDakaRecord(HttpSession session, Model model){
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        System.out.println(jobId);
        List<Attendance> records = attendanceService.findDakaRecord(jobId);
        System.out.println(records.size());
        model.addAttribute("records",records);
        return "attendance/record-list";
    }

    // 查看部门打卡记录
    @GetMapping("/find_dep_daka_record")
    public String findDepDakaRecord(HttpSession session, Model model){
        String departmentId = ((Staff) session.getAttribute("staff")).getDepartmentId();
        List<Attendance> records = attendanceService.findDepDakaRecord(departmentId);
        if (records.size() == 0){
            model.addAttribute("msg","暂无部门打卡记录");
            return "index";
        }
        model.addAttribute("records",records);
        return "attendance/dep-record-list";
    }

    // 按姓名和部门号查找打卡记录
    @PostMapping("/find_record_by_name")
    public String findDakaRecordByName(HttpSession session, Model model,@RequestParam("name")String name){
        String departmentId = ((Staff) session.getAttribute("staff")).getDepartmentId();
        List<Attendance> records = attendanceService.findDakaRecordByName(departmentId,name);
        if (records.size() == 0){
            model.addAttribute("msg","暂无该人员打卡记录");
            return "welcome";
        }
        model.addAttribute("records",records);
        return "attendance/dep-record-list";
    }
}
