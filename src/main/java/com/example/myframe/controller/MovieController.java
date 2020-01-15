package com.example.myframe.controller;

import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value="/details")
public class MovieController {
    @Autowired
    private DetailsService detailsService;

    @RequestMapping(value="/getAll")
    public RestResponse getAll(){
        List<DetailsBean> list=detailsService.getAll();
        return new RestResponse(ResultEnum.SUCCESS,list);
    }
}
