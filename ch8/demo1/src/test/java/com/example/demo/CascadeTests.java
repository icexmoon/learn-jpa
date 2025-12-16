package com.example.demo;

import com.example.demo.entity.cascade.Order2;
import com.example.demo.entity.cascade.OrderItem2;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @ClassName CascadeTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午2:33
 * @Version 1.0
 */
@SpringBootTest
public class CascadeTests {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test(){
        sessionFactory.inTransaction(session -> {
            OrderItem2 orderItem1 = new OrderItem2("Toyota", new BigDecimal("100"), 1d);
            OrderItem2 orderItem2 = new OrderItem2("Honda", new BigDecimal("200"), 2d);
            OrderItem2 orderItem3 = new OrderItem2("BMW", new BigDecimal("300"), 3d);
            Order2 order = new Order2();
            order.addOrderItem(orderItem1);
            order.addOrderItem(orderItem2);
            order.addOrderItem(orderItem3);
            session.persist(order);
            Optional<OrderItem2> bmw = order.getOrderItems().stream()
                    .filter(orderItem -> orderItem.getName().equals("BMW"))
                    .findFirst();
            if (bmw.isPresent()){
                order.getOrderItems().remove(bmw.get());
            }
        });
    }
}
