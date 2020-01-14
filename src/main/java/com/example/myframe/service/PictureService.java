package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.PictureBean;

import java.util.List;

public interface PictureService extends IService<PictureBean> {
    List<PictureBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<PictureBean> getPage(Page page);

    PictureBean get(String name);
}
