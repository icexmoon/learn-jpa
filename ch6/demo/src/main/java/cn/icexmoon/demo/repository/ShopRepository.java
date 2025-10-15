package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ShopRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:26
 * @Version 1.0
 */
public interface ShopRepository extends JpaRepository<Shop, String> {
}
