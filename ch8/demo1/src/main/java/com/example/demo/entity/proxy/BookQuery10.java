package com.example.demo.entity.proxy;

import org.hibernate.Session;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;

import java.util.List;

/**
 * @ClassName BookQuery10
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 下午2:22
 * @Version 1.0
 */
public interface BookQuery10 {
    Session session();
    @Find
    Book10 getBookByIsbn(String isbn);

    @HQL("select name,isbn from Book10 where name like concat('%',:name,'%')")
    List<Object[]> findByName(String name);

    record BookDTO(String name, String isbn) {
    }
    @HQL("select name,isbn from Book10 where name like concat('%',:name,'%')")
    List<BookDTO> findByName2(String name);
}
