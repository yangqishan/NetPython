package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("star")
@AllArgsConstructor
@NoArgsConstructor
public class StarBean extends DateBean{
    @TableId
    private int id;
    private String name;//明星姓名
    private String title;//标题
    private String content;//内容
    private int detailsId;//详情id
    private String type;//关系（basic，relationship，exp）

}
