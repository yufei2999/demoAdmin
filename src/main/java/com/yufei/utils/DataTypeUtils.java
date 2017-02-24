package com.yufei.utils;



public abstract class DataTypeUtils {
    
    /**
     * 编码格式  UTF-8
     */
    public static final String ENCODING_UTF8 = "UTF-8";
    
    /**
     * 分隔符 "/"
     */
    public static final String SEPARATOR_SLASH = "/";
    
    /**
     * 分隔符 "#"
     */
    public static final String SEPARATOR_HASHKEY = "#";

    /**
     * 配置文件名称
     */
    public static final String FILENAME_CONFIG_PROPERTIES = "config.properties";

    /**
     * error 服务异常
     */
    public static final String ERROR_SERVER_ERROR = "500";
    
    /**
     * error jsonStr 为空
     */
    public static final String ERROR_JSONSTR_BLANK = "1";

    /**
     * error 对象转换异常
     */
    public static final String ERROR_OBJECT_CONVERT_ERROR = "2";
    
    /**
     * error 必要参数为空
     */
    public static final String ERROR_NECESSARY_PARAM_BLANK = "3";
    
    /**
     * error jsonStr 或图片为空
     */
    public static final String ERROR_JSONSTR_OR_PARAM_IMAGE_BLANK = "4";
    
    /**
     * memo 服务异常
     */
    public static final String MEMO_SERVER_ERROR = "服务异常";

    /**
     * 非法操作
     */
    public static final String ILLEGAL_OPERATION = "非法操作！";

    /**
     * session key
     */
    public static final String SESSION_LOGIN_USER_KEY = "loginUser";

    /**
     * 操作类型 新增
     */
    public static final int OPERATION_ADD = 0;
    /**
     * 操作类型 更新
     */
    public static final int OPERATION_UPDATE = 1;

    /**
     * 一级菜单的父节点根路径字段的默认值
     */
    public static final String MENU_FIRST_LEVEL_PARENT_IDS = "-1";

}
