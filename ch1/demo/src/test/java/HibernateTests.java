import cn.hutool.core.date.DateUtil;
import cn.icexmoon.entity.*;
import cn.icexmoon.entity.inheritance.joined.Author3;
import cn.icexmoon.entity.inheritance.joined.Person3;
import cn.icexmoon.entity.inheritance.singletable.Author2;
import cn.icexmoon.entity.inheritance.singletable.Person2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.List;
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

    @Test
    public void testRootEntity(){
        ChildEntity child = new ChildEntity("test");
        entityManager.persist(child);
    }

    @Test
    public void testUUID(){
        Person person = new Person(new Person.Name("Jack", "Chen"),11);
        entityManager.persist(person);
    }

    @Test
    public void testJsonRead(){
        Person person = entityManager.find(Person.class, 152L);
        System.out.println(person.getName());
    }

    @Test
    public void testCar(){
        Car car = new Car("宝马","1000001");
        entityManager.persist(car);
    }

    @Test
    public void testCarNotNull(){
        Car car = new Car();
        car.setBrand("奔驰");
        car.setEngineCode("100005");
        car.setColor(Car.Color.Red);
        entityManager.persist(car);
    }

    @Test
    public void testWeek(){
        Week week = new Week();
        week.setWorkDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        week.setHolidays(EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        entityManager.persist(week);
    }

    @Test
    public void testWeekPrint(){
        Week week = entityManager.find(Week.class, 1L);
        System.out.println(week.getWorkDays());
        System.out.println(week.getHolidays());
    }

    @Test
    public void testArrayAttrs(){
        Week2 week2 = new Week2();
        week2.setHolidays(new DayOfWeek[]{DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
        week2.setWorkDays(new DayOfWeek[]{DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
        entityManager.persist(week2);
    }

    @Test
    public void testCollectionAttrs(){
        Week3 week3 = new Week3();
        week3.setHolidays(List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        week3.setWorkDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        entityManager.persist(week3);
    }

    @Test
    public void testInheritanceSingleTable(){
        Author2 author2 = new Author2();
        author2.setName("张三");
        author2.setPenName("张三丰");
        author2.setAge(18);
        entityManager.persist(author2);
        Person2 person2 = new Person2();
        person2.setName("李四");
        person2.setAge(30);
        entityManager.persist(person2);
    }

    @Test
    public void testInheritanceJoinedTable(){
        Author3 author3 = new Author3();
        author3.setName("张三");
        author3.setPenName("张三丰");
        author3.setAge(18);
        entityManager.persist(author3);
        Person3 person3 = new Person3();
        person3.setName("李四");
        person3.setAge(30);
        entityManager.persist(person3);
    }
}
