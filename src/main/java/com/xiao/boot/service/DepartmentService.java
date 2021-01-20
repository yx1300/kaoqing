package com.xiao.boot.service;

import com.xiao.boot.bean.Department;

import java.util.List;

public interface DepartmentService {

    public Integer addDepartment(Department department);

    public List<Department> findAllDepartment();

    public Department findDepartmentById(String departmentId);

    public Integer updateDepartment(Department department);

    public Integer deleteDepartmentById(String departmentId);

    public Department findDepartmentByName(String name);
}
