package com.xiao.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.Staff;
import com.xiao.boot.mapper.StaffMapper;
import com.xiao.boot.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;
    // 登录
    @Override
    public Staff login(Integer jobId, String password) {
        Staff staff = new Staff(jobId,null,null,null,null,null,password);
        // 按条件查询，如果staff属性为空，则不按该列查询
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectOne(wrapper);
    }

    @Override
    public List<Staff> findAllStaffByDepartmentId(String departmentId) {
        Staff staff = new Staff(null,null,null,null,departmentId,null,null);
        // 按条件查询，如果staff属性为空，则不按该列查询
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectList(wrapper);
    }

    @Override
    public Staff findStaffById(Integer jobId) {
        return staffMapper.selectById(jobId);
    }

    @Override
    public Integer resetCode(Integer id) {
        Staff staff = staffMapper.selectById(id);
        staff.setPassword("123456");
        return staffMapper.updateById(staff);
    }

    @Override
    public Integer addStaff(Staff staff) {
        return staffMapper.insert(staff);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffMapper.selectList(null);
    }

    @Override
    public Integer updateStaff(Staff staff) {
        return staffMapper.updateById(staff);
    }

    @Override
    public Integer deleteStaffById(Integer jobId) {
        return staffMapper.deleteById(jobId);
    }

    @Override
    public Staff findManagerByDep(String departmentId) {
        Staff staff = new Staff(null,null,null,null,departmentId,2,null);
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectOne(wrapper);
    }

    @Override
    public List<Staff> findStaffByDep(String departmentId) {
        Staff staff = new Staff();
        staff.setDepartmentId(departmentId);
        staff.setRole(3);
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectList(wrapper);
    }


}
