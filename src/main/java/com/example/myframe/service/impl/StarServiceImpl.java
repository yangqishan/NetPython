package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.StarDao;
import com.example.myframe.entity.StarBean;
import com.example.myframe.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarServiceImpl extends ServiceImpl<StarDao, StarBean> implements StarService {
    @Autowired
    private StarDao starDao;
    @Override
    public List<StarBean> getAll() {
        return starDao.getAll();
    }

    @Override
    public IPage<StarBean> getPage(Page page) {
        return starDao.getPage(page);
    }

    @Override
    public StarBean get(String name) {
        return starDao.get(name);
    }
}
