package com.miitang.gateway.sdk.util;


import com.miitang.gateway.sdk.exception.MtSdkException;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeMap;

/**
 * 类描述: object转换成map<br>
 */
public class MapUtils {

    /**
     * 将对象的本身的属性转换成map
     * @param obj
     * @return
     */
    public static TreeMap<String, String> convertToAscOrderMap(Object obj){
        TreeMap<String, String> map = new TreeMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        convertFieldToAscOrderMap(obj, map, fields, null);
        return map;
    }


    /**
     * 将对象->Object类 的所有非空字段填入map
     * @param obj
     * @param map
     * @return
     */
    public static TreeMap<String, String> convertToAscOrderMap(Object obj,TreeMap<String, String> map, Set<String> ignoreAttr){
        Class<?> aClass = obj.getClass();
        for ( ; aClass != Object.class ; aClass = aClass.getSuperclass()){
            Field[] fields = aClass.getDeclaredFields();
            convertFieldToAscOrderMap(obj, map, fields, ignoreAttr);
        }
        return map;
    }

    /**
     * 将对象的字段填入map
     * @param obj
     * @param map
     * @param fields
     */
    private static void convertFieldToAscOrderMap(Object obj, TreeMap<String, String> map, Field[] fields, Set<String> ignoreAttr) {
        for (Field field : fields) {
            String varName = field.getName();
            if(ignoreAttr == null || !ignoreAttr.contains(varName)){
                boolean accessFlag = field.isAccessible();
                field.setAccessible(true);
                Object o = null;
                try {
                    o = field.get(obj);
                } catch (IllegalAccessException e) {
                    throw new MtSdkException("object to map fail");
                }
                if (o != null && !StringUtil.isBlank(o.toString())) {
                    map.put(varName, o.toString());
                }
                field.setAccessible(accessFlag);
            }
        }
    }

}
