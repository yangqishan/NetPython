package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    @TableId
    private int id;
    private String name;
    private String pswd;
    private int age;
    private String grade;//年级
    private String sex;
    private String address;
    private String level;
    private int jifen;//积分
    private Date jointime;//加入时间
    private String img;//用户头像
}
