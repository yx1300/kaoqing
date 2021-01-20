package com.xiao.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.Attendance;
import com.xiao.boot.bean.Staff;
import com.xiao.boot.mapper.AttendanceMapper;
import com.xiao.boot.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Override
    public Integer daKa(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    @Override
    public List<Attendance> findDakaRecord(Integer jobId) {
        Attendance attendance = new Attendance();
        attendance.setJobId(jobId);
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>(attendance);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true,false,"attendance_time");
        return attendanceMapper.selectList(wrapper);
    }

    @Override
    public List<Attendance> findDepDakaRecord(String departmentId) {
        Attendance attendance = new Attendance();
        attendance.setDepartmentId(departmentId);
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>(attendance);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true,false,"attendance_time");
        return attendanceMapper.selectList(wrapper);
    }

    @Override
    public List<Attendance> findDakaRecordByName(String departmentId, String name) {
        Attendance attendance = new Attendance();
        attendance.setDepartmentId(departmentId);
        attendance.setName(name);
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>(attendance);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true,false,"attendance_time");
        return attendanceMapper.selectList(wrapper);
    }
}
