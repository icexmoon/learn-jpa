package com.example.demo;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName DataBaseTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午10:23
 * @Version 1.0
 */
@SpringBootTest
public class DataBaseTests {
    @Autowired
    private EntityManager entityManager;

    @Test
    public void test(){
        Session session = entityManager.unwrap(Session.class);
        System.out.println(session);
    }
}
