package com.nfsn.utils;

import com.nfsn.model.entity.User;

/**
 * 在当前线程中获取用户对象
 */
public class AccountHolder {

    private static ThreadLocal<User> tl =new ThreadLocal<User>();

    // 存数据
    public static void saveUser(User user){
        tl.set(user);
    }

    //取数据
    public static User getUser(){
        return tl.get();
    }

    //删除数据
    public static void removeUser(){
        tl.remove();
    }

}