package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("movie")
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity extends DateBean{
    @TableId
    private int id;
    private String name;//名字
    private String href;//下载链接
    private String content;//内容
    private int detailsId;//详情表id
}
