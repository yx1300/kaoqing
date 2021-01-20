package com.xiao.boot.service;

import com.xiao.boot.bean.Staff;

import java.util.List;

public interface StaffService {
    public Staff login(Integer jobId,String password);

    // 按部门查询所有员工
    public List<Staff> findAllStaffByDepartmentId(String departmentId);

    // 按工号查询员工
    Staff findStaffById(Integer jobId);

    // 密码重置
    public Integer resetCode(Integer id);

    // 添加员工
    public Integer addStaff(Staff staff);

    // 查找所有员工
    public List<Staff> findAllStaff();

    // 修改员工信息
    public Integer updateStaff(Staff staff);

    // 删除员工
    public Integer deleteStaffById(Integer jobId);

    // 安装部门查找部门经理是否存在
    public Staff findManagerByDep(String departmentId);

    public List<Staff> findStaffByDep(String departmentId);
}
