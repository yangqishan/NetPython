package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.PictureBean;

import java.util.List;

public interface PictureService extends IService<PictureBean> {
    List<PictureBean> getAll();

    List<PictureBean> get(String name);

    void addList(List<PictureBean> list);
}
