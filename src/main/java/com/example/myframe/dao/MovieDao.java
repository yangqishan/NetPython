package com.example.myframe.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myframe.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;
@Mapper
@Component
public interface MovieDao extends BaseMapper<MovieEntity> {

    List<MovieEntity> getAll();
    List<MovieEntity> getPage(String value);
    List<MovieEntity> get(String name);
    void addList(List<MovieEntity> list);
}
