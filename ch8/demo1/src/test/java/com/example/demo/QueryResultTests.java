package com.example.demo;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName QueryResultTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午9:49
 * @Version 1.0
 */
@SpringBootTest
public class QueryResultTests {
    @Autowired
    private SessionFactory sessionFactory;
    @Test
    public void test(){
        sessionFactory.inTransaction(session -> {
            List<Object[]> cars = session.createSelectionQuery("select brand,owner from Car2", Object[].class)
                    .getResultList();
            for (Object[] car : cars) {
                Object brand = car[0];
                Object owner = car[1];
                System.out.printf("brand:%s,owner:%s\n", brand, owner);
            }
        });
    }

    @Test
    public void test2(){
        sessionFactory.inTransaction(session -> {
            record CarDto(String brand, String owner) {
            };
            List<CarDto> cars = session.createSelectionQuery("select brand,owner from Car2", CarDto.class)
                    .setMaxResults(10)
                    .getResultList();
            for (CarDto car : cars) {
                String brand = car.brand();
                String owner = car.owner();
                System.out.printf("brand:%s,owner:%s\n", brand, owner);
            }
        });
    }
}
