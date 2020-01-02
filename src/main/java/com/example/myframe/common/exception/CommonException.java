/*******************************************************************************
 * Copyright (c) 2005, 2014 myspring.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.example.myframe.common.exception;

import com.example.myframe.common.consts.ResultEnum;

/**
 * 定义自定义业务异常
 *
 * @author zouxuanping
 */
public class CommonException extends RuntimeException {

    private Integer code;
    private String msg;

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
