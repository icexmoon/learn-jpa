import cn.hutool.core.date.DateUtil;
import cn.icexmoon.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName HibernateTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午3:44
 * @Version 1.0
 */
public class HibernateTests {
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private EntityManagerFactory entityManagerFactory;
    @BeforeEach
    void beforeEach() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    void afterEach() {
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void test(){
        Student student = new Student("李四");
        entityManager.persist(student);
        ClassItem classItem = new ClassItem("java");
        entityManager.persist(classItem);
        StudentClass studentClass = new StudentClass(
                new StudentClass.StudentClassId(classItem.getId(), student.getId()),
                DateUtil.parseDate("2025-1-25"), DateUtil.parseDate("2025-10-25"));
        entityManager.persist(studentClass);
    }

    @Test
    public void testFind(){
        StudentClass studentClass = entityManager.find(StudentClass.class, new StudentClass.StudentClassId(1L, 1L));
        System.out.println(studentClass);
    }

    @Test
    public void testNoVersion() throws InterruptedException {
        // 先准备一些数据
        Book book = new Book("MySQL 应知应会", new BigDecimal("10.0"));
        entityManager.persist(book);
        transaction.commit();
        transaction.begin();
        Book book1 = entityManager.find(Book.class, book.getId());
        book1.setPrice(new BigDecimal("20.0"));
        // 模拟在某个地方被其他线程修改了
        CountDownLatch  countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            transaction.commit();
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Book book3 = entityManager.find(Book.class, book.getId());
            book3.setPrice(new BigDecimal("40.0"));
            entityManager.merge(book3);
            transaction.commit();
            transaction.begin();
            System.out.println("另外一个线程修改了数据");
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        entityManager.merge(book1);
        System.out.println("当前线程修改了数据");
    }

    @Test
    public void testBaseEntity(){
        School school = new School("上海大学");
        entityManager.persist(school);
    }

    @Test
    public void testSchoolUpdate(){
        School school = entityManager.find(School.class, 1L);
        school.setName("四川大学");
        entityManager.merge(school);
    }
}
