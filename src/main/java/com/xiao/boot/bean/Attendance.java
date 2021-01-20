package com.xiao.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance implements Serializable {
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    private String attendanceTime;
    private Integer jobId;
    private String name;
    private String sex;
    private String birthday;
    private String departmentId;
    private Integer attendanceType;
}
