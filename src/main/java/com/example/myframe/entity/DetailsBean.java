package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("details")
@NoArgsConstructor
@AllArgsConstructor
public class DetailsBean extends DateBean{
    @TableId
    private int id;
    private String name;//名称
    private String content;//内容
    private String img;//照片绝对路径
    private String type;//类型
}
