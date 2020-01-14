package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.CareerBean;

import java.util.List;

public interface CareerService extends IService<CareerBean> {
    List<CareerBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<CareerBean> getPage(Page page);

    CareerBean get(String name);
}
