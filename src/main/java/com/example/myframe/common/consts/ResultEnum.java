package com.example.myframe.common.consts;

public enum ResultEnum {
    /**
     * 系统错误码 标准化
     */
    /**
     * 参数格式错误
     */
    PARAM_TYPE_ERROR(198, "参数格式错误"),
    /**
     * 未知错误 服务器出了点小问题
     */
    UNKNOW_ERROR(199, "系统异常,请联系在线客服"),
    /**
     * 成功 没有异常 请求成功
     */
    SUCCESS(200, "成功"),
    /**
     * 参数不正确
     */
    PARAM_ERROR(201, "参数不正确"),
    /**
     * 无效的token
     */
    INVALID_TOKEN(202, "无效的token"),
    /**
     * 过期的token
     */
    TOKEN_EXPIRED(203, "过期的token"),
    /**
     * 没有权限
     */
    UNAUTHORIZED(204, "没有权限"),
    /**
     * 删除失败
     */
    DELETE_FAILED(205, "删除失败"),
    /**
     * 资源不存在
     */
    RESOURCE_NOT_FOUND(206, "数据不存在或被删除"),
    /**
     * 网络超时
     */
    READ_TIMEED_OUT(207, "网络开小差了,请稍后再试"),
    /**
     * 数据库数据不存在
     */
    DB_NOT_FOUND(208, "数据库数据不存在"),
    /**
     * 请求的路径不存在
     */
    URL_NOT_FOUND(404, "请求的路径不存在"),;


    ResultEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    private Integer code;
    private String message;


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
}
