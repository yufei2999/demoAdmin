package com.yufei.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ContextClassLoaderLocal;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class BeanUtil {

    private static final Log log = LogFactory.getLog(BeanUtil.class);

    static BeanUtilsBean beanUtils = null;

    static {
        final DateConverter dc = new DateConverter();
        dc.setPatterns(new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "HH:mm", "yyyy/MM/dd"});
        Converter dtConverter = new Converter() {
            @SuppressWarnings("rawtypes")
            public Object convert(Class arg0, Object arg1) {
                if (StringUtils.isBlank((String) arg1)) {
                    return null;
                }
                return dc.convert(arg0, arg1);
            }
        };
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.deregister(Date.class);
        convertUtilsBean.register(dtConverter, Date.class);
        beanUtils = new BeanUtilsBean(convertUtilsBean);
    }

    private static final ContextClassLoaderLocal BEANS_BY_CLASSLOADER = new ContextClassLoaderLocal() {
        protected Object initialValue() {
            return new BeanUtil();
        }
    };

    public static BeanUtil getInstance() {
        return (BeanUtil) BEANS_BY_CLASSLOADER.get();
    }

    public static BeanUtilsBean get() {
        return beanUtils;
    }

    public static void setEmptyNoException(Object obj) {
        try {
            setEmptyForNull(obj);
        } catch (Exception e) {
            log.error("some errors occurred", e);
        }
    }

    public static void setEmptyForNull(Object obj) throws Exception {
        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodSuffix = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String getMethod = "get" + methodSuffix;
            if (!containMethod(clazz, getMethod)) {
                continue;
            }
            Method method = clazz.getMethod(getMethod, new Class[0]);
            if (!"java.lang.String".equalsIgnoreCase(method.getReturnType().getName())) {
                continue;
            }
            Object value = method.invoke(obj, new Object[0]);
            if (value == null) {
                String setMethod = "set" + methodSuffix;
                if (containMethod(clazz, setMethod)) {
                    method = clazz.getMethod(setMethod, new Class[]{String.class});
                    method.invoke(obj, new Object[]{""});
                }
            }
        }
    }

    public static boolean containMethod(Class<? extends Object> clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 金额格式化 police
     *
     * @param s 金额 默认2位小数
     * @return 格式后的金额
     */
    public static String numberFormat2String(Double s) {
        return numberFormat2String(s, 2);
    }

    /**
     * 金额格式化 police
     *
     * @param s 金额 默认2位小数
     * @return 格式后的金额
     */
    public static String numberFormat2String(String s) {
        return numberFormat2String(s, 2);
    }

    /**
     * 金额格式化 police
     *
     * @param s   金额
     * @param len 小数位数
     * @return 格式后的金额
     */
    public static String numberFormat2String(Double s, int len) {
        if (s == null || s == 0) {
            return "0.00";
        }
        return numberFormat2String(s.toString(), 2);
    }

    /**
     * 金额格式化 police
     *
     * @param s   金额
     * @param len 小数位数
     * @return 格式后的金额
     */
    public static String numberFormat2String(String s, int len) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = Double.parseDouble(s);
        if (len == 0) {
            formater = new DecimalFormat("###,###");

        } else {
            StringBuffer buff = new StringBuffer();
            buff.append("###,###.");
            for (int i = 0; i < len; i++) {
                buff.append("0");
            }
            formater = new DecimalFormat(buff.toString());
        }
        return formater.format(num);
    }

    /**
     * 金额去掉“,” police
     *
     * @param s 金额
     * @return 去掉“,”后的金额
     */
    public static String revertNumberFormat(String s) {
        String formatString = "";
        if (s != null && s.length() >= 1) {
            formatString = s.replaceAll(",", "");
        }
        return formatString;
    }

    /**
     * 从MAP中copy属性到对象中
     *
     * @param object
     * @param dataMap
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyFromMap(Object object, Map<String, Object> dataMap) throws IllegalAccessException, InvocationTargetException {
        if (object == null || dataMap == null || dataMap.isEmpty())
            return;
        beanUtils.copyProperties(object, dataMap);
    }

    /**
     * 复制属性
     *
     * @param src
     * @param targetMap
     */
    public static void copy2Map(Object src, Map<String, Object> targetMap) {

        if (src == null || targetMap == null) {
            return;
        }

        PropertyDescriptor[] srcprops = null;
        try {
            srcprops = Introspector.getBeanInfo(src.getClass()).getPropertyDescriptors();
            for (int i = 0; i < srcprops.length; i++) {
                Method srcGet = srcprops[i].getReadMethod();
                Object srcReturn = srcGet.invoke(src);
                if (srcReturn instanceof Class) {
                    continue;
                }
                targetMap.put(srcprops[i].getName(), srcReturn);
            }
        } catch (Exception e) {
            log.error("copy2map error", e);
        }
    }

    /**
     * 简单javabean属性复制工具类（包括String、两种Date、基本类型以及其包装类型）<br/>
     * 字符串转日期类型和日期转字符串类型仅用于 mm
     * null覆盖目标属性对应值
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copySimpleProperties(Object dest, Object src) {
        copySimpleProperties(dest, src, false);
    }

    /**
     * 简单javabean属性复制工具类（包括String、两种Date、基本类型以及其包装类型）<br/>
     * 字符串转日期类型和日期转字符串类型仅用于 mm
     *
     * @param dest 目标对象
     * @param src  源对象
     * @param b    null值是否覆盖目标对象的属性  false:覆盖，true：不覆盖
     */
    public static void copySimpleProperties(Object dest, Object src, boolean b) {
        if (src == null || dest == null) {
            return;
        }
        PropertyDescriptor[] srcprops = null;
        PropertyDescriptor[] destprops = null;
        try {
            srcprops = Introspector.getBeanInfo(src.getClass()).getPropertyDescriptors();
            destprops = Introspector.getBeanInfo(dest.getClass()).getPropertyDescriptors();
            if (srcprops == null || destprops == null) {
                return;
            }
            for (int i = 0; i < srcprops.length; i++) {
                for (int j = 0; j < destprops.length; j++) {
                    if (srcprops[i].getName().equals(destprops[j].getName())
                            && srcprops[i].getReadMethod() != null && srcprops[i].getWriteMethod() != null
                            && destprops[j].getReadMethod() != null && destprops[j].getWriteMethod() != null
                            && srcprops[i].getPropertyType() != null && destprops[j].getPropertyType() != null
                            && destprops[j].getPropertyType().equals(srcprops[i].getPropertyType())
                            && (srcprops[i].getPropertyType().equals(Integer.class)
                            || srcprops[i].getPropertyType().equals(int.class)
                            || srcprops[i].getPropertyType().equals(Double.class)
                            || srcprops[i].getPropertyType().equals(double.class)
                            || srcprops[i].getPropertyType().equals(Float.class)
                            || srcprops[i].getPropertyType().equals(float.class)
                            || srcprops[i].getPropertyType().equals(String.class)
                            || srcprops[i].getPropertyType().equals(Date.class)
                            || srcprops[i].getPropertyType().equals(java.sql.Date.class)
                            || srcprops[i].getPropertyType().equals(short.class)
                            || srcprops[i].getPropertyType().equals(Short.class)
                            || srcprops[i].getPropertyType().equals(byte.class)
                            || srcprops[i].getPropertyType().equals(Byte.class)
                            || srcprops[i].getPropertyType().equals(char.class)
                            || srcprops[i].getPropertyType().equals(Character.class)
                            || srcprops[i].getPropertyType().equals(long.class)
                            || srcprops[i].getPropertyType().equals(Long.class)
                    )) {
                        Method srcGet = srcprops[i].getReadMethod();
                        Object srcReturn = srcGet.invoke(src);
                        //null是否覆盖目标对象属性
                        if (b) {
                            if (srcReturn != null) {
                                Method destSet = destprops[j].getWriteMethod();
                                destSet.invoke(dest, srcReturn);
                            }
                        } else {
                            Method destSet = destprops[j].getWriteMethod();
                            destSet.invoke(dest, srcReturn);
                        }

                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.error("copy simple properties error", e);
        }
    }

    /**
     * 根据key对集合进行分组  police
     *
     * @param keyString  集合中的对象属性名
     * @param sourceList 要分组的源集合
     * @param <T>
     * @return
     */
    public static <T> Map<Object, List<T>> groupByKeyString(String keyString, List<T> sourceList) {
        return groupByKeyString(keyString, sourceList, null);
    }

    public static <T> Map<Object, List<T>> groupByKeyString(String keyString, List<T> sourceList, Boolean b) {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            Map<Object, List<T>> result = null;
            if (b == null) {//按put顺序
                result = new LinkedHashMap<>();
            } else if (b) {//按自然顺序
                result = new TreeMap<>();
            } else {//无序
                result = new HashMap<>();
            }
            for (T t : sourceList) {
                try {
                    Field field = t.getClass().getDeclaredField(keyString);
                    Method method = t.getClass().getMethod(getGetterMethod(field));
                    Object obj = method.invoke(t);
                    if (obj == null) {
                        continue;
                    }
                    if (result.containsKey(obj)) {
                        List<T> list = result.get(obj);
                        list.add(t);
                    } else {
                        List<T> list = new ArrayList<T>();
                        list.add(t);
                        result.put(obj, list);
                    }
                } catch (Exception e) {
                    log.error("some errors occurred", e);
                    return null;
                }
            }
            return result;
        }
        return null;
    }


    public static <T> Map<Object, T> groupByKeyString2Obj(String keyString, List<T> sourceList) {
        return groupByKeyString2Obj(keyString, sourceList, false);
    }

    public static <T> Map<Object, T> groupByKeyString2Obj(String keyString, List<T> sourceList, Boolean b) {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            Map<Object, T> result = null;
            if (b == null) {//按put顺序
                result = new LinkedHashMap<>();
            } else if (b) {//按自然顺序
                result = new TreeMap<>();
            } else {//无序
                result = new HashMap<>();
            }
            for (T t : sourceList) {
                try {
                    Field field = t.getClass().getDeclaredField(keyString);
                    Method method = t.getClass().getMethod(getGetterMethod(field));
                    Object obj = method.invoke(t);
                    result.put(obj, t);
                } catch (Exception e) {
                    log.error("some errors occurred", e);
                    return null;
                }
            }
            return result;
        }
        return null;
    }

    public static String getSetterMethod(Field field) {
        return "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    public static String getGetterMethod(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    public static <T> void copyCollection(Collection<T> dests, Collection srcs, Class<T> clazz) {
        try {
            if (CollectionUtils.isNotEmpty(srcs)) {
                for (Object src : srcs) {
                    Object dest = clazz.newInstance();
                    copySimpleProperties(dest, src);
                    dests.add((T) dest);
                }
            }
        } catch (Exception e) {
            log.error("some errors occurred", e);
        }
    }

    /**
     * 将集合根据符号拼接成字符串
     *
     * @param list
     * @param sign
     * @return
     */
    public static String joinListWithSign(List<String> list, String sign) {
        StringBuffer sb = new StringBuffer();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String str : list) {
                sb.append(str);
                sb.append(sign);
            }
            sb.delete(sb.lastIndexOf(sign), sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将字符串数组根据符号拼接成字符串
     *
     * @param array
     * @param sign
     * @return
     */
    public static String joinArrayWithSign(String[] array, String sign) {
        StringBuffer sb = new StringBuffer();
        if (array != null && array.length > 0) {
            for (String str : array) {
                sb.append(str);
                sb.append(sign);
            }
            sb.delete(sb.lastIndexOf(sign), sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 克隆集合，并返回克隆后的集合
     *
     * @param srcs
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> cloneCollection(Collection<? extends Cloneable> srcs, Class<T> clazz) {
        try {
            if (CollectionUtils.isNotEmpty(srcs)) {
                List<T> desc = new ArrayList<T>();
                for (Cloneable src : srcs) {
                    desc.add((T) Reflections.invokeMethodByName(src, "clone", null));
                }
                return desc;
            }
        } catch (Exception e) {
            log.error("some errors occurred", e);
        }
        return null;
    }

}
