package com.example.myframe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.entity.MovieEntity;

import java.util.List;

public interface DetailsService extends IService<DetailsBean> {
    List<DetailsBean> getAll(String type);
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<DetailsBean> getPage(Page page);

    DetailsBean get(String name);
    void add(DetailsBean detailsBean);
}
