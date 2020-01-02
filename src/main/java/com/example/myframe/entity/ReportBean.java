package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@TableName("report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportBean {
    @TableId
    private int id;
    private String name;
    private String subject;
    private String title;
    private int paperId;
    private int sum;//题目数量
    private int achievement;//成绩
    private Date addtime;//考试时间
    private String ranswer;//试卷答案
}
