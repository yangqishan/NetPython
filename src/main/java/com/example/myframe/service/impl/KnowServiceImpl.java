package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.KnowDao;
import com.example.myframe.entity.KnowBean;
import com.example.myframe.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowServiceImpl extends ServiceImpl<KnowDao, KnowBean> implements KnowService {
    @Autowired
    private KnowDao knowDao;
    @Override
    public List<KnowBean> getAll() {
        return knowDao.getAll();
    }

    @Override
    public IPage<KnowBean> getPage(Page page) {
        return null;
    }

    @Override
    public KnowBean get(String name) {
        return knowDao.get(name);
    }
}
