package com.yufei.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Properties;

public abstract class PropertiesUtil {

    private static final Log log = LogFactory.getLog(PropertiesUtil.class);
    private static Properties properties = null;

    /**
     * 获取指定properties文件指定key的value值
     * 
     * @param key properties的key值
     * @param propertyName properties 文件名称
     * @return 返回value值
     */
    public static String getValue(String propertyName, String key) {
        try {
            if (properties == null) {
                properties = new Properties();
            }
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyName));
            return properties.getProperty(key);
        } catch (IOException e) {
            log.error("####### 解析properties文件异常", e);
        }
        return null;
    }
}
