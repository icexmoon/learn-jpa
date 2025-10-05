package cn.icexmoon;

import cn.icexmoon.entity.Customer;
import jakarta.persistence.*;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @ClassName EntityManagerTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 上午11:56
 * @Version 1.0
 */
public class EntityManagerTests {
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
        if (transaction != null) {
            if (transaction.isActive()) {
                transaction.commit();
            }
        }
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testFind() {
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println("-----------------------");
        System.out.println(customer);
    }

    @Test
    public void testGetReferenceException() {
        Customer customer = entityManager.getReference(Customer.class, 1);
        transaction.commit();
        entityManager.close();
        System.out.println("-----------------------");
        Assertions.assertThrowsExactly(LazyInitializationException.class, () -> {
            System.out.println(customer);
        });
    }

    @Test
    public void testGetReference() {
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println("-----------------------");
        System.out.println(customer);
    }

    @Test
    public void testPersistence(){
        Customer customer = new Customer();
        customer.setLastName("icexmoon");
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreateTime(new Date());
        entityManager.persist(customer);
    }

    @Test
    public void testPersistenceException(){
        Customer customer = new Customer();
        customer.setLastName("icexmoon");
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreateTime(new Date());
        customer.setId(11L);
        Assertions.assertThrowsExactly(EntityExistsException.class, () -> {
            entityManager.persist(customer);
        });
    }

    @Test
    public void testRemove(){
        Customer customer = entityManager.find(Customer.class, 1);
        entityManager.remove(customer);
    }

    @Test
    public void testRemoveException(){
        Customer customer = new Customer();
        customer.setId(1L);
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            entityManager.remove(customer);
        });
    }

    /**
     * merge 参数是一个临时实体
     */
    @Test
    public void testMerge1(){
        Customer customer = new Customer();
        customer.setLastName("icexmoon");
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreateTime(new Date());
        Customer merge = entityManager.merge(customer);
        // 临时实体的 ID 依然是 null
        Assertions.assertNull(customer.getId());
        // merge 方法返回的是一个持久实体，ID 不为 null
        Assertions.assertNotNull(merge.getId());
    }

    /**
     * merge 参数是一个游离实体
     */
    @Test
    public void testMerge2(){
        Customer customer = new Customer();
        customer.setId(3L);
        customer.setLastName("Tom");
        Customer merge = entityManager.merge(customer);
        Assertions.assertEquals("Tom", merge.getLastName());
        Assertions.assertEquals(3L, merge.getId());
    }

    /**
     * merge 参数是一个游离实体，且 EntityManager 中已经存在该实体
     */
    @Test
    public void testMerge3(){
        Customer customer = new Customer();
        customer.setId(3L);
        customer.setLastName("Jim");
        Customer customerExists = entityManager.find(Customer.class, 3L);
        Customer merge = entityManager.merge(customer);
        Assertions.assertEquals("Jim", merge.getLastName());
        Assertions.assertEquals(3L, merge.getId());
        Assertions.assertSame(customerExists, merge);
    }

    /**
     * merge 的参数是一个游离实体，且 EntityManager 中不存在该实体，且数据库中也不存在该实体
     */
    @Test
    public void testMerge4(){
        Customer customer = new Customer();
        customer.setId(99L);
        customer.setLastName("Bruce");
        Assertions.assertThrowsExactly(OptimisticLockException.class, ()->{
            entityManager.merge(customer);
        });
    }

    @Test
    public void testFlush(){
        Customer customer = entityManager.find(Customer.class, 3L);
        customer.setLastName("Li Lei");
        entityManager.flush();
    }

    @Test
    public void testRefresh(){
        Customer customer = entityManager.find(Customer.class, 3L);
        customer = entityManager.find(Customer.class, 3L);
        // 这里会强制执行 SELECT 更新实体数据
        entityManager.refresh(customer);
    }

    @Test
    public void testContains(){
        Customer customer = entityManager.find(Customer.class, 3L);
        Assertions.assertTrue(entityManager.contains(customer));
        entityManager.clear();
        Assertions.assertFalse(entityManager.contains(customer));
    }
}
