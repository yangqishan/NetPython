package com.example.myframe.entity;

import lombok.Data;

@Data
public class NewBean extends DateBean {
    private String title;//标题
    private String href;//链接
    private String img;//照片
}
