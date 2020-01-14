package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myframe.dao.CareerDao;
import com.example.myframe.entity.CareerBean;
import com.example.myframe.service.CareerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerServiceImpl extends ServiceImpl<CareerDao, CareerBean> implements CareerService {
    @Autowired
    private CareerDao careerDao;
    @Override
    public List<CareerBean> getAll() {
        return careerDao.getAll();
    }

    @Override
    public IPage<CareerBean> getPage(Page page) {
        return careerDao.getPage(page);
    }

    @Override
    public CareerBean get(String name) {
        return careerDao.get(name);
    }
}
