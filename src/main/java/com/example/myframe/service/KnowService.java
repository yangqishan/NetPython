package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.KnowBean;

import java.util.List;

public interface KnowService extends IService<KnowBean> {
    List<KnowBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<KnowBean> getPage(Page page);

    KnowBean get(String name);
}
