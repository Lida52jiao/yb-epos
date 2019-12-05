package com.yjkj.util;

/**
 * Created by bin on 2017/11/7.
 */
public class MyStringUtil {
    public static String effectiveTimeGetYear(String str){
        String [] strs=str.split("-");
        return strs[1];
    }
    public static String effectiveTimeGetMonth(String str){
        String [] strs=str.split("-");
        return strs[0];
    }
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str){
        return str != null && str.length() > 0;
    }
    public static boolean isBlank(String str){
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }
    public static boolean isNotBlank(String str){
        return str != null && str.length() > 0 && str.trim().length() > 0;
    }
    public static Integer valueOf(String value, int def) {
        try {
            if (isNotEmpty(value)) {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
            return def;
        }
        return def;
    }
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
