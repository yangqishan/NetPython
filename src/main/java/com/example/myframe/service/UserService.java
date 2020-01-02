package com.example.myframe.service;


import com.example.myframe.entity.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> getAll();

    UserBean get(String name);
}
