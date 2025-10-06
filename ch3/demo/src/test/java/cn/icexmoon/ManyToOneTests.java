package cn.icexmoon;

import cn.icexmoon.entity.Customer;
import cn.icexmoon.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName ManyToOneTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午5:13
 * @Version 1.0
 */
public class ManyToOneTests {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    public void destroy() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setLastName("张三");
        customer.setAge(18);
        customer.setBirth(LocalDate.of(1990, 1, 1));
        customer.setCreateTime(LocalDateTime.now());
        Order order1 = new Order("订单1", customer);
        Order order2 = new Order("订单2", customer);
        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    @Test
    public void testAdd2() {
        Customer customer = new Customer();
        customer.setLastName("张三");
        customer.setAge(18);
        customer.setBirth(LocalDate.of(1990, 1, 1));
        customer.setCreateTime(LocalDateTime.now());
        Order order1 = new Order("订单1", customer);
        Order order2 = new Order("订单2", customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(customer);
    }

    @Test
    public void testGet() {
        Order order = entityManager.find(Order.class, 10L);
        System.out.println(order.getCustomer());
    }

    @Test
    public void testUpdate() {
        Order order = entityManager.find(Order.class, 10L);
        order.getCustomer().setLastName("李四");
        entityManager.merge(order);
    }

    @Test
    public void testDelete() {
        Order order = entityManager.find(Order.class, 10L);
        entityManager.remove(order);
    }

    @Test
    public void testDelete2() {
        Order order = entityManager.find(Order.class, 9L);
        // 会因为外键约束报错
        entityManager.remove(order.getCustomer());
    }
}
