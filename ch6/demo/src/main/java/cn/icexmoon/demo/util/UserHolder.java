package cn.icexmoon.demo.util;

import cn.icexmoon.demo.entity.User;

/**
 * @ClassName UserHolder
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 下午3:21
 * @Version 1.0
 */
public class UserHolder {
    private static final ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static void setCurrentUser(User user) {
        userHolder.set(user);
    }
}
