package com.example.myframe.aop;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private String[] admins = {"杨瑞安", "weixin", "xiaoshitou"};

    //是否是管理员
    boolean isAdmin(String name) {

        return Arrays.asList(admins).contains(name);
    }
}
