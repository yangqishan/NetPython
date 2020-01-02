package com.example.myframe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myframe.entity.ReportBean;

import java.util.List;

public interface BaseService extends IService<ReportBean>{
    List<ReportBean> get();

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<ReportBean> getPage(Page<ReportBean> page);
}
