package com.example.demo.entity;

import org.hibernate.Session;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.Pattern;

import java.util.List;

/**
 * @ClassName CarQuery2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午10:41
 * @Version 1.0
 */
public interface CarQuery4 {
    Session session();
    @Find
    List<Car2> findByBrandAndOwner(String brand, String owner);

    @Find
    List<Car2> findByBrand(@Pattern String brand);
}
