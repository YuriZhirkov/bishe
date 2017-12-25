package com.example.k8s.untils;

import java.io.Serializable;

/**
 * Created by HQ on 2017/1/6.
 */
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 1695144100735980507L;

    public static String CODE_SUCCESS = "00";//成功
    public static String CODE_IP_ACCESS_DENIED = "01";//IP鉴权失败
    public static String CODE_PARAMETER_ERROR = "02";//参数错误
    public static String CODE_NOT_EXIST = "03";//对象不存在
    public static String CODE_OTHER = "11";//其它错误

    private String code;//错误代码
    private String message = "";//响应消息

    public ApiResponse() {
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        if (message != null)
            this.message = message;
        else
            this.message = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message != null)
            this.message = message;
        else
            this.message = "";
    }
}
