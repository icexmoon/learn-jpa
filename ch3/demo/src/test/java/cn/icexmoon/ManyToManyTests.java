package cn.icexmoon;

import cn.icexmoon.entity.m2m.Course;
import cn.icexmoon.entity.m2m.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @ClassName ManyToManyTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午8:23
 * @Version 1.0
 */
public class ManyToManyTests {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    public void destroy() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testAdd() {
        Course course1 = new Course("JPA");
        Course course2 = new Course("Hibernate");
        Student student1 = new Student("张三");
        Student student2 = new Student("李四");
        student1.getCourses().add(course1);
        student1.getCourses().add(course2);
        course1.getStudents().add(student1);
        course2.getStudents().add(student1);
        student2.getCourses().add(course1);
        course1.getStudents().add(student2);
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(course1);
        entityManager.persist(course2);
    }

    @Test
    public void testFind() {
        Student student = entityManager.find(Student.class, 1L);
        System.out.println("----------------");
        System.out.println(student.getCourses());
    }
}
