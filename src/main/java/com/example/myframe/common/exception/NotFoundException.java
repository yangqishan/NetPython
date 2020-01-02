package com.example.myframe.common.exception;


import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.http.IpUtils;
import com.example.myframe.common.response.ErrResponse;
import com.example.myframe.common.http.IpUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义404异常处理
 *
 * @author zouxuanping
 */
@Controller
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }


    /**
     * 情况1：若预期返回类型为text/html,则返回错误信息页(View).
     */
    /*@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("forward:/404.html");
        return mv;
    }*/

    /**
     * 情况2：其它预期类型 则返回详细的错误信息(JSON).
     */
    /*@RequestMapping
    public ModelAndView error(HttpServletRequest request) {

        ModelAndView mv=new ModelAndView();
        mv.setViewName("forward:/404.html");
        return mv;
    }*/


}

