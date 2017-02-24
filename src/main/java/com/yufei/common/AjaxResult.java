package com.yufei.common;

import java.io.Serializable;

/**
 * Created by pc on 2016-09-28.
 */
public class AjaxResult implements Serializable {

    /**
     * 成功
     */
    public final static String RESULT_CODE_0000 = "0000";
    /**
     * 失败
     */
    public final static String RESULT_CODE_0001 = "0001";
    /**
     * 相应状态码 如：RESULT_CODE_0000
     */
    private String code;
    /**
     * 返回的提示信息
     */
    private String message;
    /**
     * 返回的具体数据对象
     */
    private Object data;

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
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
