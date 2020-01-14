package com.example.myframe.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.NewBean;


import java.util.List;

public interface NewService extends IService<NewBean> {
    List<NewBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<NewBean> getPage(Page page);

    NewBean get(String name);
}
