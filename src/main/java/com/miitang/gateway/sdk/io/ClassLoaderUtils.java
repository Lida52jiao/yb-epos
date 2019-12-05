package com.miitang.gateway.sdk.io;

/**
 * 类描述: 加载jar或者classPath下文件<br>
 */
public final class ClassLoaderUtils {

    /**
     * 获取类加载器
     * @return
     */
    public static  ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

}