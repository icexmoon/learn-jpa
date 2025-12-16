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
public interface CarQuery3 {
    Session session();
    default List<Car2> findByBrandAndOwner(String brand, String owner){
        Session session = session();
        return session.createSelectionQuery("from Car2 where brand like concat('%',:brand,'%') and owner like concat('%',:owner,'%')", Car2.class)
                .setParameter("brand", brand)
                .setParameter("owner", owner)
                .getResultList();
    }

    @HQL("from Car2 where brand like concat('%',:brand,'%')")
    List<Car2> findByBrand(String brand);

    @HQL("from Car2 where owner like concat('%',:owner,'%')")
    List<Car2> findByOwner(String owner);
}
