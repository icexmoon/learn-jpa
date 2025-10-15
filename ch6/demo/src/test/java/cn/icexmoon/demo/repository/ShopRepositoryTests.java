package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName ShopRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:27
 * @Version 1.0
 */
@SpringBootTest
public class ShopRepositoryTests {
    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void testInsert() {
        Shop shop1 = new Shop("面包店");
        Shop shop2 = new Shop("水果店");
        Shop shop3 = new Shop("甜点店");
        List<Shop> shops = List.of(shop1, shop2, shop3);
        shopRepository.saveAll(shops);
    }
}
