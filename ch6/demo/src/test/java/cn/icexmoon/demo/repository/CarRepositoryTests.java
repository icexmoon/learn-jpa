package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName CarRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:16
 * @Version 1.0
 */
@SpringBootTest
public class CarRepositoryTests {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testInsert() {
        Car car1 = new Car("奔驰");
        Car car2 = new Car("保时捷");
        Car car3 = new Car("法拉利");
        List<Car> cars = List.of(car1, car2, car3);
        carRepository.saveAll(cars);
    }
}
