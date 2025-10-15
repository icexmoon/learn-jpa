package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Order;
import cn.icexmoon.demo.entity.User;
import cn.icexmoon.demo.util.UserHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName OrderRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午11:21
 * @Version 1.0
 */
@SpringBootTest
public class OrderRepositoryTests {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void addOrder() {
        Order entity = new Order();
        entity.setName("Jack");
        entity.setRegionCode("130102");
        orderRepository.save(entity);
    }

    @Test
    public void pageAll() {
        UserHolder.setCurrentUser(new User(1L, "icexmoon", "130102", 1));
        Pageable pageable = PageRequest.of(0, 5);
        Page<Order> orderPage = orderRepository.pageAll(1L, pageable);
        System.out.println(orderPage.getContent());
    }
}
