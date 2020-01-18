package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.DetailsDao;
import com.example.myframe.dao.MovieDao;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.entity.MovieEntity;
import com.example.myframe.service.DetailsService;
import com.example.myframe.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 详情service层
 */
@Service
public class DetailsServiceImpl extends ServiceImpl<DetailsDao, DetailsBean> implements DetailsService {
    @Autowired
    private DetailsDao detailsDao;
    @Override
    public List<DetailsBean> getAll(String type) {
        return detailsDao.getAll(type);
    }

    @Override
    public IPage<DetailsBean> getPage(Page page) {
        return null;
    }

    @Override
    public  DetailsBean get(String name) {
        return detailsDao.get(name);
    }

    @Override
    public void add(DetailsBean detailsBean) {
        detailsDao.add(detailsBean);
    }

}
