package cn.icexmoon;

import cn.icexmoon.entity.o2o.Department;
import cn.icexmoon.entity.o2o.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @ClassName OneToOneTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午7:53
 * @Version 1.0
 */
public class OneToOneTests {
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
        Manager manager = new Manager("张三");
        Department department = new Department("开发部");
        manager.setDepartment(department);
        department.setManager(manager);
        entityManager.persist(department);
        entityManager.persist(manager);
    }

    @Test
    public void testFind() {
        Manager manager = entityManager.find(Manager.class, 1L);
        System.out.println(manager.getDepartment());
    }
}
