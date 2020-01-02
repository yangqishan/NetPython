package com.example.myframe.common.response;

import com.example.myframe.common.consts.ResultEnum;

import java.io.Serializable;

/**
 * 异常时返回数据
 *
 * @author zouxuanping
 */
public class ErrResponse implements Serializable {

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 访客ip
     */
    private String ip;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ErrResponse(ResultEnum resultEnum, String ip) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.ip = ip;
    }

    public ErrResponse(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public ErrResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrResponse(Integer code, String message, String ip) {
        this.code = code;
        this.message = message;
        this.ip = ip;
    }
}
