package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.NewDao;
import com.example.myframe.entity.NewBean;
import com.example.myframe.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewServiceImpl extends ServiceImpl<NewDao, NewBean> implements NewService {
    @Autowired
    private NewDao newDao;
    @Override
    public List<NewBean> getAll() {
        return newDao.getAll();
    }

    @Override
    public IPage<NewBean> getPage(Page page) {
        return newDao.getPage(page);
    }

    @Override
    public NewBean get(String name) {
        return newDao.get(name);
    }
}
