package cn.icexmoon;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PersistenceTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 上午10:33
 * @Version 1.0
 */
public class PersistenceTests {
    @Test
    public void testCreateEntityManagerFactory(){
        @Cleanup EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
        Assertions.assertNotNull(entityManagerFactory);
    }

    @Test
    public void testCreateEntityManagerFactory2(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", false);
        @Cleanup EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo", properties);
        Assertions.assertNotNull(entityManagerFactory);
    }
}
