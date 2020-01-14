package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.GoodsBean;

import java.util.List;

public interface GoodsService extends IService<GoodsBean> {
    List<GoodsBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<GoodsBean> getPage(Page page);

    GoodsBean get(String name);
}
