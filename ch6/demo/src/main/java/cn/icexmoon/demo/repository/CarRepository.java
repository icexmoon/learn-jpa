package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CarRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:16
 * @Version 1.0
 */
public interface CarRepository extends JpaRepository<Car, String> {
}
