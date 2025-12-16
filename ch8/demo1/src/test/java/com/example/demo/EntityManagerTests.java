package com.example.demo;

import com.example.demo.entity.Car2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName EntityManagerTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午10:45
 * @Version 1.0
 */
@SpringBootTest
public class EntityManagerTests {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
        entityManager.close();
    }

    @Test
    public void test(){
        System.out.println(entityManager);
    }

    @Test
    public void testTransaction(){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            Car2 car = new Car2();
            car.setBrand("Toyota");
            car.setOwner("icexmoon@qq.com");
            em.persist(car);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
        finally{
            em.close();
        }
    }

    @Test
    public void testTransaction2(){
    }
}
