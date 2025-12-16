package com.example.demo;

import com.example.demo.entity.proxy.Book10;
import com.example.demo.entity.proxy.Publisher10;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.graph.RootGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName ProxyTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午3:20
 * @Version 1.0
 */
@SpringBootTest
public class ProxyTests {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test(){
        sessionFactory.inTransaction(session -> {
            Publisher10 publisher = new Publisher10();
            publisher.setName("海南文艺出版社");
            Publisher10 publisher2 = new Publisher10();
            publisher2.setName("北京大地出版社");
            Publisher10 publisher3 = new Publisher10();
            publisher3.setName("上海文艺出版社");
            Book10 book = new Book10();
            book.setName("鲁滨孙漂流记");
            book.setIsbn("1234567890");
            book.addPublisher(publisher, true);
            book.addPublisher(publisher2, true);
            Book10 book2 = new Book10();
            book2.setName("白夜行");
            book2.setIsbn("1234567891");
            book2.addPublisher(publisher3, true);
            book2.addPublisher(publisher, true);
            session.persist(book);
            session.persist(book2);
        });
    }

    @Test
    public void test2(){
        sessionFactory.inTransaction(session -> {
            Publisher10 publisher = session.get(Publisher10.class, 1L);
            boolean initialized = Hibernate.isInitialized(publisher.getBooks());
            Assertions.assertFalse(initialized);
            Hibernate.initialize(publisher.getBooks());
            initialized = Hibernate.isInitialized(publisher.getBooks());
            Assertions.assertTrue(initialized);
        });
    }

    @Test
    public void test3(){
        sessionFactory.inTransaction(session -> {
            Publisher10 publisher = session.get(Publisher10.class, 1L);
            Book10 book1 = session.getReference(Book10.class, 1L);
            Book10 book2 = session.getReference(Book10.class, 2L);
            boolean contains1 = Hibernate.contains(publisher.getBooks(), book1);
            boolean contains2 = Hibernate.contains(publisher.getBooks(), book2);
            System.out.println(contains1);
            System.out.println(contains2);
        });
    }

    @Test
    public void test4(){
        sessionFactory.inTransaction(session -> {
            Publisher10 publisher = session.get(Publisher10.class, 1L);
            publisher.getBooks().forEach(System.out::println);
        });
    }

    @Test
    public void test5(){
        sessionFactory.inTransaction(session -> {
            RootGraph<Publisher10> entityGraph = session.createEntityGraph(Publisher10.class);
            entityGraph.addSubgraph("books");
            Publisher10 publisher = session.byId(Publisher10.class).withFetchGraph(entityGraph).load(1L);
            publisher.getBooks().forEach(System.out::println);
        });
    }
}
