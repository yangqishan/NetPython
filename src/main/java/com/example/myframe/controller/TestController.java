package com.example.myframe.controller;

import com.example.myframe.aop.Permission;
import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.redis.RedisUtils;
import com.example.myframe.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    private static  final Logger logger= LoggerFactory.getLogger(TestController.class);


    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value="/find")
    public String get(HttpServletRequest request){
        //List<ReportBean> list=baseService.get();
        //request.getSession().setAttribute("list",list);
        request.setAttribute("current",1);
        request.setAttribute("size",5);

        return "index";
    }



    @RequestMapping(value = "/login")
    public ModelAndView  login(HttpServletRequest request){
        String name=(String) request.getSession().getAttribute("name");
        ModelAndView mv = new ModelAndView();
        if(name==null){
            mv.setViewName("forward:/login.html");
        }else{
            boolean flag=redisUtils.hasKey(name);
            if(flag==false){
                mv.setViewName("forward:/login.html");
            }else{
                //手动显式指定使用转发，此时springmvc.xml配置文件中的视图解析器将会失效
                mv.setViewName("forward:/index.jsp");
            }
        }
        return mv;

    }
    @RequestMapping(value ="/userlogin")
    @ResponseBody
    public String user(@RequestParam(value="name") String name,
                       @RequestParam(value="password") String pswd,
                       HttpServletRequest request){


        if(true){
            request.getSession().setAttribute("name",name);
            //将用户的密码 账号存到缓存 定时60秒
            redisUtils.set(name,pswd,60);
            return "登录成功";
        }else{
            return "密码错误";
        }
    }
    @RequestMapping(value = "/findByname")
    @Permission
    @ResponseBody
    public RestResponse findByname(){
        return new RestResponse(ResultEnum.SUCCESS,"有权限");
    }

}
