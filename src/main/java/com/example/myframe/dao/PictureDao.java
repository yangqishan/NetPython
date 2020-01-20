package com.example.myframe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myframe.entity.PictureBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PictureDao extends BaseMapper<PictureBean> {

    List<PictureBean> getAll();


    List<PictureBean> get(String name);

    void addList(List<PictureBean> list);
}


