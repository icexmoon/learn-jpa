package cn.icexmoon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @ClassName EntityManagerFactoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 上午10:59
 * @Version 1.0
 */
public class EntityManagerFactoryTests {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
    }

    @AfterAll
    public static void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateEntityManager() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Assertions.assertNotNull(entityManager);
    }
}
