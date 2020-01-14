package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.MovieDao;
import com.example.myframe.entity.MovieEntity;
import com.example.myframe.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieDao, MovieEntity> implements MovieService {
    @Autowired
    private MovieDao movieDao;
    @Override
    public List<MovieEntity> getAll() {
        return movieDao.getAll();
    }

    @Override
    public IPage<MovieEntity> getPage(Page page) {
        return null;
    }

    @Override
    public  List<MovieEntity> get(String name) {
        return movieDao.get(name);
    }

}
