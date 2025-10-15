package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName UserRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/9 上午11:28
 * @Version 1.0
 */
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Tom");
        user.setAge(30);
        userRepository.updateUser(user);
    }

    @Test
    public void testAdd(){
        User user = new User();
        user.setName("Tom");
        user.setAge(18);
        userRepository.save(user);
    }

    @Test
    public void testUpdateUserNotNull() {
        User user = new User();
        user.setId(1L);
        user.setName("Jack");
        userRepository.updateUserNotNull(user);
    }

    @Test
    public void testUpdateUserNotNull2() {
        User user = new User();
        user.setId(1L);
        user.setName("Bruce");
        userRepository.saveOrUpdateByNotNullProperties(user);
    }
}
