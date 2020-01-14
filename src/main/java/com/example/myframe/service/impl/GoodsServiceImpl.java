package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.GoodsDao;
import com.example.myframe.entity.GoodsBean;
import com.example.myframe.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsBean>  implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsBean> getAll() {
        return goodsDao.getAll();
    }

    @Override
    public IPage<GoodsBean> getPage(Page page) {
        return goodsDao.getPage(page);
    }

    @Override
    public GoodsBean get(String name) {
        return goodsDao.get(name);
    }
}
