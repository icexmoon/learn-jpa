package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.entity.proxy.Book10;
import com.example.demo.entity.proxy.BookQuery10;
import com.example.demo.entity.proxy.BookQuery10_;
import org.hibernate.SessionFactory;
import org.hibernate.query.Order;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName NamedQueryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午10:00
 * @Version 1.0
 */
@SpringBootTest
public class NamedQueryTests {
    @Autowired
    private SessionFactory sessionFactory;
    @Test
    public void test() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createNamedQuery("Car2.findByBrand", Car2.class)
                    .setParameter("brand", "T")
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test2() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createNamedQuery(Car2_.QUERY_CAR2_FIND_BY_BRAND, Car2.class)
                    .setParameter(Car2_.BRAND, "T")
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test3() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = Car2_.findByOwner(session, "Chen");
            System.out.println(cars);
        });
    }

    @Test
    public void test4() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = CarQuery2_.findByBrandAndOwner(session, "T", "Chen");
            System.out.println(cars);
        });
    }

    @Test
    public void test5() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = CarQuery2_.findByBrandAndOwner2(session, "T", "Chen");
            System.out.println(cars);
        });
    }

    @Test
    public void test6() {
        sessionFactory.inTransaction(session -> {
            CarQuery3_ query = new CarQuery3_(session);
            List<Car2> cars = query.findByBrandAndOwner( "T", "Chen");
            System.out.println(cars);
        });
    }

    @Test
    public void test7() {
        sessionFactory.inTransaction(session -> {
            CarQuery4_ query = new CarQuery4_(session);
            List<Car2> cars = query.findByBrandAndOwner( "Toyota", "Chen");
            System.out.println(cars);
        });
    }

    @Test
    public void test8() {
        sessionFactory.inTransaction(session -> {
            CarQuery5_ query = new CarQuery5_(session);
            List<Car2> cars = query.findByBrand( "o", Page.page(10, 0), Order.asc(Car2_.brand));
            System.out.println(cars);
        });
    }

    @Test
    public void test9() {
        sessionFactory.inTransaction(session -> {
            BookQuery10_ query = new BookQuery10_(session);
            Book10 book = query.getBookByIsbn("1234567890");
            System.out.println(book);
        });
    }

    @Test
    public void test10() {
        sessionFactory.inTransaction(session -> {
            BookQuery10_ query = new BookQuery10_(session);
            List<Object[]> books = query.findByName("白夜行");
            for (Object[] book : books) {
                System.out.printf("《%s》,isbn:%s\n", book[0],book[1]);
            }
        });
    }

    @Test
    public void test11() {
        sessionFactory.inTransaction(session -> {
            BookQuery10_ query = new BookQuery10_(session);
            List<BookQuery10.BookDTO> books = query.findByName2("白夜行");
            for (BookQuery10.BookDTO book : books) {
                System.out.printf("《%s》,isbn:%s\n", book.name(),book.isbn());
            }
        });
    }
}
