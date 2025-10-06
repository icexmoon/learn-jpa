package cn.icexmoon;

import cn.icexmoon.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;



/**
 * @ClassName ${NAME}
 * @Description ${DESCRIPTION}
 * @Author icexmoon@qq.com
 * @Date 2025/10/3 下午7:15
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setLastName("icexmoon");
        customer.setAge(18);
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        ZoneId zoneId = ZoneId.systemDefault();
        customer.setBirth(LocalDate.of(2000, 1, 1));
        customer.setCreateTime(LocalDateTime.now());
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}