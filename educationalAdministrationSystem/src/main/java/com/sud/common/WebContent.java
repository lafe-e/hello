package com.sud.common;

import com.sud.user.entity.User;

public class WebContent {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected User initialValue()
        {
            return null;
        }
    };

    private static WebContent webContent = new WebContent();

    public synchronized static WebContent getInstance(){
        return webContent;
    }

    public ThreadLocal<User> getUserThreadLocal() {
        return userThreadLocal;
    }
}
