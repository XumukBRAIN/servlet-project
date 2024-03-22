package com.dev.servlet.security;

public class SecurityContextHolder {
    private static final ThreadLocal<UserInfo> userInfoHolder = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return userInfoHolder.get();
    }

    public static void setUserInfo(UserInfo userInfo) {
        userInfoHolder.set(userInfo);
    }

    public static void clear() {
        userInfoHolder.remove();
    }
}
