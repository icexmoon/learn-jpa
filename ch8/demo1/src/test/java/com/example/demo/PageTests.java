package com.example.demo;

import com.example.demo.entity.Car2;
import org.hibernate.SessionFactory;
import org.hibernate.query.Order;
import org.hibernate.query.Page;
import org.hibernate.query.SelectionQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName PageTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午9:12
 * @Version 1.0
 */
@SpringBootTest
public class PageTests {
    @Autowired
    private SessionFactory sessionFactory;
    @Test
    public void test(){
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2", Car2.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test2(){
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2", Car2.class)
                    .setPage(Page.page(10, 0))
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test3(){
        sessionFactory.inTransaction(session -> {
            SelectionQuery<Car2> query = session.createSelectionQuery("from Car2", Car2.class);
            long resultCount = query.getResultCount();
            final int PAGE_SIZE = 10;
            List<Car2> cars = query
                    .setPage(Page.page(PAGE_SIZE, 0))
                    .getResultList();
            System.out.println(cars);
            // 总页数
            long pageCount = (resultCount + PAGE_SIZE - 1) / PAGE_SIZE;
            System.out.printf("查询结果有%d条，共%d页\n", resultCount, pageCount);
        });
    }

    @Test
    public void test4(){
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2 order by id asc limit 10 offset 0", Car2.class)
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test5(){
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2", Car2.class)
                    .setOrder(List.of(Order.asc(Car2.class,"brand"), Order.desc(Car2.class,"id")))
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println(cars);
        });
    }
}
