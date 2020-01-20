package com.example.myframe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("picture")
@AllArgsConstructor
@NoArgsConstructor
public class PictureBean extends DateBean{
    @TableId
    private int id;
    private String name;
    private String img;
}
