package com.example.demo;

import com.example.demo.entity.Car2;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName FlushTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午4:38
 * @Version 1.0
 */
@SpringBootTest
public class FlushTests {
    @Autowired
    private SessionFactory sessionFactory;
    @Test
    public void test(){
        Session openedSession = sessionFactory.openSession();
        Transaction transaction = openedSession.beginTransaction();
        Car2 car = new Car2();
        car.setBrand("Toyota");
        car.setOwner("icexmoon@qq.com");
        // 添加实体
        openedSession.persist(car);
        // 这里的查询与添加的实体无关，不会触发刷新
        List<?> books = openedSession.createSelectionQuery("from Book10")
                .getResultList();
        // 事务提交时触发刷新
        transaction.commit();
        openedSession.close();
    }

    @Test
    public void test2(){
        Session openedSession = sessionFactory.openSession();
        Transaction transaction = openedSession.beginTransaction();
        Car2 car = new Car2();
        car.setBrand("Toyota");
        car.setOwner("icexmoon@qq.com");
        // 添加实体
        openedSession.persist(car);
        // 这里的查询与添加的实体有关，触发刷新
        List<?> cars = openedSession.createSelectionQuery("from Car2")
                .getResultList();
        // 事务提交时没有改变，不再触发刷新
        transaction.commit();
        openedSession.close();
    }

    @Test
    public void test3(){
        Session openedSession = sessionFactory.openSession();
        Transaction transaction = openedSession.beginTransaction();
        Car2 car = new Car2();
        car.setBrand("Toyota");
        car.setOwner("icexmoon@qq.com");
        // 添加实体
        openedSession.persist(car);
        // 这里手动刷新会话
        openedSession.flush();
        // 这里的查询与添加的实体有关，但不再触发会话刷新（已经刷新过了）
        List<?> cars = openedSession.createSelectionQuery("from Car2")
                .getResultList();
        // 事务提交时没有改变，不再触发刷新
        transaction.commit();
        openedSession.close();
    }

    @Test
    public void test4(){
        Session openedSession = sessionFactory.openSession();
//        openedSession.setFlushMode(FlushModeType.COMMIT);
        openedSession.setHibernateFlushMode(FlushMode.MANUAL);
        Transaction transaction = openedSession.beginTransaction();
        Car2 car = new Car2();
        car.setBrand("BWM");
        car.setOwner("Jack Chen");
        openedSession.persist(car);
        // 这里没有刷新会话，会返回脏数据
        List<?> cars = openedSession.createSelectionQuery("from Car2")
                .getResultList();
        System.out.println(cars);
        transaction.commit();
        openedSession.close();
    }
}
