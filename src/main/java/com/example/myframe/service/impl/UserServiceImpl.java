package com.example.myframe.service.impl;

import com.example.myframe.dao.UserDao;
import com.example.myframe.entity.UserBean;
import com.example.myframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<UserBean> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserBean get(String name) {
        return userDao.get(name);
    }
}
