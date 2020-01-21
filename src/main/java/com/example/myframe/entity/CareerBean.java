package com.example.myframe.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CareerBean extends DateBean{
    private int id;
    private String name;
    private String title;//职位
    private String salary;//工资
    private String img;//照片
    private String company;//公司
    private String href;//跳转链接
    private String min;//最低工资
    private String max;//最高
}
