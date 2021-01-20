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
//@TableName("staff") // 默认和类名一样
public class Staff implements Serializable {
    @TableId(value = "job_id",type=IdType.AUTO)
    private Integer jobId;
    private String name;
    private String sex;
    private String birthday;
    private String departmentId;
    private Integer role;
    private String password;
}
