package cn.icexmoon;

import cn.icexmoon.entity.Car;
import cn.icexmoon.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @ClassName OneToManyTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午6:47
 * @Version 1.0
 */
public class OneToManyTests2 {
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
        Person person = new Person("张三");
        Car car1 = new Car("ford");
        Car car2 = new Car("bmw");
        person.getCars().add(car1);
        person.getCars().add(car2);
        car1.setOwner(person);
        car2.setOwner(person);
        entityManager.persist(person);
        entityManager.persist(car1);
        entityManager.persist(car2);
    }
}
