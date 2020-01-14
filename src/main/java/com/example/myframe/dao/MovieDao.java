package com.example.myframe.dao;


import com.example.myframe.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;
@Mapper
@Component
public interface MovieDao {

    List<MovieEntity> getAll();
    List<MovieEntity> get(String value);
}
