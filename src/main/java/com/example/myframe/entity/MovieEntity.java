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
public class MovieEntity {
    @TableId
    private int id;
    private String name;
    private String href;
    private String content;
}
