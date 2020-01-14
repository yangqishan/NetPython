package com.example.myframe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myframe.entity.StarBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StarDao extends BaseMapper<StarBean> {

    List<StarBean> getAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<StarBean> getPage(Page page);
    StarBean get(String name);

}


