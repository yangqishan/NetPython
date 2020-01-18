package com.example.myframe.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 详情Dao层
 */
@Mapper
@Component
public interface DetailsDao extends BaseMapper<DetailsBean> {
    //获取全部
    List<DetailsBean> getAll(String type);
    DetailsBean get(String name);
    void add(DetailsBean detailsBean);
}
