package com.example.demo;

import com.example.demo.entity.Car2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName SessionTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午11:04
 * @Version 1.0
 */
@SpringBootTest
public class SessionTests {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    @BeforeEach
    public void beforeEach(){
        session = sessionFactory.openSession();
    }

    @AfterEach
    public void afterEach(){
        session.close();
    }

    @Test
    public void test(){
        System.out.println(session);
    }

    @Test
    public void testTransaction(){
        Session openedSession = sessionFactory.openSession();
        Transaction transaction = openedSession.beginTransaction();
        try{
            Car2 car = new Car2();
            car.setBrand("Toyota");
            car.setOwner("icexmoon@qq.com");
            openedSession.persist(car);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
        finally{
            openedSession.close();
        }
    }

    @Test
    public void testTransaction2(){
        sessionFactory.inTransaction(session -> {
            Car2 car = new Car2();
            car.setBrand("Toyota");
            car.setOwner("icexmoon@qq.com");
            session.persist(car);
        });
    }

    @Test
    public void testTransaction3(){
        Car2 toyota = sessionFactory.fromTransaction(session -> {
            Car2 car = new Car2();
            car.setBrand("Toyota");
            car.setOwner("icexmoon@qq.com");
            session.persist(car);
            return car;
        });
        System.out.println(toyota);
    }
}
