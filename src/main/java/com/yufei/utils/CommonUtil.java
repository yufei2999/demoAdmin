package com.yufei.utils;

import com.yufei.sys.model.SysUser;
import com.yufei.sys.model.result.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by pc on 2016-10-31.
 */
public abstract class CommonUtil {

    private static final Log log = LogFactory.getLog(Reflections.class);

    /**
     * 新增、更新时初始化属性值
     *
     * @param obj
     * @param type
     */
    public static void initSetProperties(Object obj, int type) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserInfo user = (UserInfo) request.getSession().getAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY);
        if (user == null) {
            log.info("session is null");
            return;
        }
        try {
            if (type == DataTypeUtils.OPERATION_ADD) {
                Reflections.setFieldValue(obj, "creator", user.getId());
                Reflections.setFieldValue(obj, "createTime", new Date());
                Reflections.setFieldValue(obj, "deleted", 1);
            }
            Reflections.setFieldValue(obj, "lastModifier", user.getId());
            Reflections.setFieldValue(obj, "lastModifyTime", new Date());
        } catch (Exception e) {
            log.error("新增、更新时初始化属性值异常", e);
        }
    }
}
