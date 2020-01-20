package com.example.myframe.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myframe.dao.PictureDao;
import com.example.myframe.entity.PictureBean;
import com.example.myframe.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureDao, PictureBean> implements PictureService {
    @Autowired
    private PictureDao pictureDao;
    @Override
    public List<PictureBean> getAll() {
        return pictureDao.getAll();
    }

    @Override
    public List<PictureBean> get(String name) {
        return pictureDao.get(name);
    }

    @Override
    public void addList(List<PictureBean> list) {
        pictureDao.addList(list);
    }


}
