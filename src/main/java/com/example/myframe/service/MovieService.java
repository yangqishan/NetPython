package com.example.myframe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.MovieEntity;

import java.util.List;

public interface MovieService extends IService<MovieEntity> {
    List<MovieEntity> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<MovieEntity> getPage(Page page);

    List<MovieEntity> get(String name);
}
