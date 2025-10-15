package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Order;
import com.blinkfox.fenix.jpa.FenixJpaRepository;
import com.blinkfox.fenix.jpa.QueryFenix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName OrderRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午11:21
 * @Version 1.0
 */
public interface OrderRepository extends FenixJpaRepository<Order, Long> {
    @QueryFenix
    Page<Order> pageAll(@Param("uid") Long uid, Pageable pageable);
}
