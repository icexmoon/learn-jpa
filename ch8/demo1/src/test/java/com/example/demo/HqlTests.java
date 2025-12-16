package com.example.demo;

import com.example.demo.dto.CarSearchDTO;
import com.example.demo.entity.Car2;
import com.example.demo.entity.proxy.Book10;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.criteria.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName HqlTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午5:11
 * @Version 1.0
 */
@SpringBootTest
public class HqlTests {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void test() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2 where brand like :brand", Car2.class)
                    .setParameter("brand", "Toyota")
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test2() {
        List<Car2> cars = entityManager.createQuery("from Car2 where brand like :brand", Car2.class)
                .setParameter("brand", "Toyota")
                .getResultList();
        System.out.println(cars);
    }

    @Test
    public void test3() {
        sessionFactory.inTransaction(session -> {
            List<Car2> cars = session.createSelectionQuery("from Car2 where brand like ?1", Car2.class)
                    .setParameter(1, "Toyota")
                    .getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test4() {
        sessionFactory.inTransaction(session -> {
            Book10 book = session.createSelectionQuery("from Book10 where isbn=:isbn", Book10.class)
                    .setParameter("isbn", "1234567891")
                    .getSingleResult();
            System.out.println(book);
        });
    }

    @Test
    public void test5() {
        sessionFactory.inTransaction(session -> {
            Book10 book = session.createSelectionQuery("from Book10 where isbn=?1", Book10.class)
                    .setParameter(1, "12345678911")
                    .getSingleResultOrNull();
            System.out.println(book);
        });
    }

    @Test
    public void test6() {
        sessionFactory.inTransaction(session -> {
            Book10 book = session.createSelectionQuery("from Book10 where isbn=?1", Book10.class)
                    .setParameter(1, "12345678911")
                    .setHibernateFlushMode(FlushMode.COMMIT)
                    .getSingleResultOrNull();
            System.out.println(book);
        });
    }

    @Test
    public void test7() {
        sessionFactory.inTransaction(session -> {
            CarSearchDTO dto = new CarSearchDTO();
            dto.setBrand("WM");
            dto.setOwner("Chen");
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Car2> query = criteriaBuilder.createQuery(Car2.class);
            JpaRoot<Car2> car = query.from(Car2.class);
            JpaPredicate where = criteriaBuilder.conjunction();
            if (dto.getBrand() != null){
                where = criteriaBuilder.and(where, criteriaBuilder.like(car.get("brand"), "%" + dto.getBrand() + "%"));
            }
            if (dto.getOwner() != null){
                where = criteriaBuilder.and(where, criteriaBuilder.like(car.get("owner"), "%" + dto.getOwner() + "%"));
            }
            query.select(car).where(where).orderBy(criteriaBuilder.asc(car.get("id")));
            List<Car2> cars = session.createSelectionQuery(query).getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test8() {
        sessionFactory.inTransaction(session -> {
            CarSearchDTO dto = new CarSearchDTO();
            dto.setBrand("WM");
            dto.setOwner("Chen");
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaDelete<Car2> delete = criteriaBuilder.createCriteriaDelete(Car2.class);
            JpaRoot<Car2> car = delete.from(Car2.class);
            JpaPredicate where = criteriaBuilder.conjunction();
            if (dto.getBrand() != null){
                where = criteriaBuilder.and(where, criteriaBuilder.like(car.get("brand"), "%" + dto.getBrand() + "%"));
            }
            if (dto.getOwner() != null){
                where = criteriaBuilder.and(where, criteriaBuilder.like(car.get("owner"), "%" + dto.getOwner() + "%"));
            }
            delete.where(where);
            session.createMutationQuery(delete).executeUpdate();
        });
    }

    @Test
    public void test9() {
        sessionFactory.inTransaction(session -> {
            NativeQuery<Car2> query = session.createNativeQuery("select * from car2 where brand like :brand", Car2.class);
            query.setParameter("brand", "%" + "T" + "%");
            List<Car2> cars = query.getResultList();
            System.out.println(cars);
        });
    }

    @Test
    public void test10() {
        sessionFactory.inTransaction(session -> {
            NativeQuery<String> query = session.createNativeQuery("select owner from car2 where brand like :brand", String.class);
            query.setParameter("brand", "%" + "T" + "%");
            List<String> owners = query.getResultList();
            System.out.println(owners);
        });
    }

    @Test
    public void test11() {
        sessionFactory.inTransaction(session -> {
            session.setHibernateFlushMode(FlushMode.AUTO);
            Car2 car = new Car2();
            car.setBrand("Toyota");
            car.setOwner("Chen");
            session.persist(car);
            List<Car2> cars = session.createNativeQuery("select * from car2", Car2.class)
                    .setHibernateFlushMode(FlushMode.ALWAYS)
                    .getResultList();
            System.out.println(cars);
        });
    }
}
