package com.example.myframe.common.exception;


import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.http.IpUtils;
import com.example.myframe.common.http.RequestUtils;
import com.example.myframe.common.response.ErrResponse;
import org.jsoup.HttpStatusException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author zouxuanping
 * 全局异常 其他地方不用做异常处理 直接抛出来
 * 不能404异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    public static final String DEV_ENVIRONMENT = "dev";

    @Value("${spring.profiles.active}")
    private String isDev;


    /*@ExceptionHandler(value = {IllegalArgumentException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntity<String> parameterErrorHandler(HttpServletRequest req, IllegalArgumentException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }*/

    /**
     * 全局异常抛出
     * Throwable.class
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrResponse handleException(HttpServletRequest req, Exception ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return new ErrResponse(ResultEnum.PARAM_ERROR, IpUtils.getIP(req));
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return new ErrResponse(ResultEnum.PARAM_TYPE_ERROR, IpUtils.getIP(req));
        }
        if (ex instanceof SocketTimeoutException
                || ex instanceof NoRouteToHostException
                || ex instanceof ConnectException
                || ex instanceof HttpStatusException
                || ex instanceof UnknownHostException) {
            return new ErrResponse(ResultEnum.READ_TIMEED_OUT, IpUtils.getIP(req));
        }
        StringBuffer message = new StringBuffer();
        message.append("全局异常抛出: request error at ").append(req.getRequestURL())
                .append("请求的所有参数为：").append(RequestUtils.getAllRequestParam(req));

        if (ex instanceof NumberFormatException) {
            return new ErrResponse(ResultEnum.READ_TIMEED_OUT, IpUtils.getIP(req));
        }

        return new ErrResponse(ResultEnum.UNKNOW_ERROR, IpUtils.getIP(req));
    }


    /**
     * 自定义异常抛出
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ErrResponse handleServiceException(HttpServletRequest req, final CommonException ex) {
        if (DEV_ENVIRONMENT.equals(isDev)) {

        }
        return new ErrResponse(ex.getCode(), ex.getMsg(), IpUtils.getIP(req));
    }

}
