package cn.icexmoon;

import cn.icexmoon.entity.Order;
import org.junit.jupiter.api.Test;

/**
 * @ClassName CacheTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/6 下午5:26
 * @Version 1.0
 */
public class CacheTests extends EntityTestsBase{
    @Test
    public void testCache() {
        Order order1 = entityManager.find(Order.class, 1L);
        // 提交事务，重启一个事务
        entityTransaction.commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Order order2 = entityManager.find(Order.class, 1L);
        System.out.println(order1.getOrderName());
    }
}
