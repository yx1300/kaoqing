package com.xiao.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.Department;
import com.xiao.boot.bean.Staff;
import com.xiao.boot.mapper.DepartmentMapper;
import com.xiao.boot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public Integer addDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.selectList(null);
    }

    @Override
    public Department findDepartmentById(String departmentId) {
        return departmentMapper.selectById(departmentId);
    }

    @Override
    public Integer updateDepartment(Department department) {
        return departmentMapper.updateById(department);
    }

    @Override
    public Integer deleteDepartmentById(String departmentId) {
        return departmentMapper.deleteById(departmentId);
    }

    @Override
    public Department findDepartmentByName(String name) {
        Department department = new Department(null,name);
        // 按条件查询，如果staff属性为空，则不按该列查询
        QueryWrapper<Department> wrapper = new QueryWrapper<>(department);
        return departmentMapper.selectOne(wrapper);
    }
}
