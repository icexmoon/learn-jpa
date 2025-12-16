package com.example.demo.entity;

import org.hibernate.Session;
import org.hibernate.annotations.processing.HQL;

import java.util.List;

/**
 * @ClassName CarQuery2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午10:41
 * @Version 1.0
 */
public interface CarQuery2 {
    @HQL("from Car2 where brand like concat('%',:brand,'%') and owner like concat('%',:owner,'%')")
    List<Car2> findByBrandAndOwner(String brand, String owner);

    @HQL("from Car2 where brand like concat('%',:brand,'%') and owner like concat('%',:owner,'%')")
    List<Car2> findByBrandAndOwner2(Session session, String brand, String owner);
}
