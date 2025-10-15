package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.User;
import com.blinkfox.fenix.jpa.FenixJpaRepository;
import com.blinkfox.fenix.jpa.QueryFenix;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/9 上午11:09
 * @Version 1.0
 */
public interface UserRepository extends FenixJpaRepository<User, Long> {
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @QueryFenix
    void updateUser(@Param("user") User user);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @QueryFenix
    void updateUserNotNull(@Param("user") User user);


}
