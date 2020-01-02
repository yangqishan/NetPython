package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.BaseDao;
import com.example.myframe.entity.ReportBean;
import com.example.myframe.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl extends ServiceImpl<BaseDao,ReportBean> implements BaseService {
    @Autowired
    private BaseDao baseDao;
    @Override
    public List<ReportBean> get() {
        return baseDao.getAll();
    }

    @Override
    public IPage<ReportBean> getPage(Page<ReportBean> page) {
        return baseDao.getPage(page);
    }
}
