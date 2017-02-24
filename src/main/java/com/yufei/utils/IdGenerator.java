package com.yufei.utils;

import java.util.UUID;

/**
 * Created by pc on 2016-10-24.
 */
public abstract class IdGenerator {

    public static String genUUIDStr(){
        String str = UUID.randomUUID().toString();
        return str.replaceAll("-", "");
    }

}
