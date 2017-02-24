package com.yufei.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;

public abstract class MD5 {

    private static final Log log = LogFactory.getLog(MD5.class);
    
    private static final int SIZE = 2;
    private static final int KeyConstant = 64;
    private static final int CORPSCHAR = 0xff;

    private static String toHex(byte[] bytesArray) {
        StringBuffer sb = new StringBuffer(KeyConstant);
        String str = "";
        for (byte b : bytesArray) {
            str = Integer.toHexString(b & CORPSCHAR);
            if (str.length() < SIZE) {
                sb.append('0');
            }
            sb.append(str);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * MD5加密
     * 
     * @param s
     * @return
     */
    public static String encode(String s) {
        try {
            if (StringUtils.isBlank(s)) {
                return null;
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            return toHex(md.digest());
        } catch (Exception e) {
            log.error("MD5加密异常", e);
        }
        return null;
    }
    
}
