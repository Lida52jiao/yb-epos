package com.yjkj.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用org.apache.commons.beanutils进行转换 
 */

/**
 * 使用Introspector进行转换
 */
public class MapUtil {

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    public static HashMap<String, Object> uriStringToMap(String str){
        HashMap<String,Object> map= Maps.newHashMap();
        String [] strs=str.split("&");
        for (int i=0;i<strs.length;i++){
            String [] kv=strs[i].split("=");
            String k=kv[0];
            String v=kv[1];
            map.put(k,v);
        }
        return map;
    }

    public static void main(String[] args) {
        String json= JSON.toJSONString(uriStringToMap("signData=3D2AC5050711E82B7CC60DCA3BDC4A82&data=UizrB0%252BStFcG%252FZwAergFDydv%252BhSeM1jk9N54pblm14W6xWnP8kk%252BGdWaSV25q8VFDy5WKIQYYcAv%250D%250AaAVr4QftDGRcLisyQxIjYwuXe9iVezf7bMZzjGUS%252BND4ZWHZ%252FdUffB8V03bwXa7gze0l3P9plLju%250D%250Ajog9JZpR3UdJUhrS8o6bQJ9KEmiIuJ6TS1nWKKP%252BTmUp1x%252F3%252BZ73v%252FVXsZTtkY%252Fl3CJ5bCDHKAUe%250D%250AeXRInYbZN%252BFyiSdouwiyctF0xLeZLPh8MAxnLgViLGtY7qz9xJlXleiUmvVCm9oPyxaQ5yWC6x7%252F%250D%250AZpR1OYrVGM9WR3JJA3EGtxoRahmqmDAQ02AumEJTMiIFE7FWmWhugF%252Bg5gWVhVyR51AUyiqQh7hQ%250D%250AO%252BZ9UsViOAywtwyvd33f6QKbB9kOHv7M70U1gbfgplod4vSUBLSLpidPANU46ZO5%252BkZPNb2N1%252Fjq%250D%250AglNJIZdfQyABqON4bE%252BEvb57iZlQlyHO830ru60wO4vaFMhWYqqcF7XzzOG0pqoIK%252FMTjWj3pWpc%250D%250AnQ%253D%253D%250D%250A&parentMerchantNo=1046407769"));
        System.out.println(json);
    }

}  