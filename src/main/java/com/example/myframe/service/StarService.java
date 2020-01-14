package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.StarBean;

import java.util.List;

public interface StarService extends IService<StarBean> {
    List<StarBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<StarBean> getPage(Page page);

    StarBean get(String name);
}
