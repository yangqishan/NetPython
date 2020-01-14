package com.example.myframe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myframe.entity.NewBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NewDao extends BaseMapper<NewBean> {

    List<NewBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<NewBean> getPage(Page page);
    NewBean get(String name);

}


