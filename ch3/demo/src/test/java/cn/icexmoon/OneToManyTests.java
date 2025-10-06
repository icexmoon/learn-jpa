package cn.icexmoon;

import cn.icexmoon.entity.Child;
import cn.icexmoon.entity.Toy;
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
public class OneToManyTests {
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
        Child child = new Child("小王");
        Toy toy1 = new Toy("玩具1");
        Toy toy2 = new Toy("玩具2");
        child.getToys().add(toy1);
        child.getToys().add(toy2);
        entityManager.persist(child);
        entityManager.persist(toy1);
        entityManager.persist(toy2);
    }

    @Test
    public void testGet() {
        Child child = entityManager.find(Child.class, 1L);
        System.out.println(child.getToys());
    }

    @Test
    public void testUpdate() {
        Child child = entityManager.find(Child.class, 1L);
        child.getToys().getFirst().setName("奥特曼");
    }

    @Test
    public void testDelete() {
        Child child = entityManager.find(Child.class, 2L);
        entityManager.remove(child);
    }
}
