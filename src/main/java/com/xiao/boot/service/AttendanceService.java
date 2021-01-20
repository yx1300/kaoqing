package com.xiao.boot.service;

import com.xiao.boot.bean.Attendance;

import java.util.List;

public interface AttendanceService {
    public Integer daKa(Attendance attendance);

    public List<Attendance> findDakaRecord(Integer jobId);

    public List<Attendance> findDepDakaRecord(String departmentId);

    public List<Attendance> findDakaRecordByName(String departmentId,String name);
}
