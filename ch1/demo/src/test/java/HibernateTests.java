import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.icexmoon.entity.*;
import cn.icexmoon.entity.column.Book4;
import cn.icexmoon.entity.columnlength.Book6;
import cn.icexmoon.entity.columnlength.Book7;
import cn.icexmoon.entity.columnlength.Book8;
import cn.icexmoon.entity.columnlength.Book9;
import cn.icexmoon.entity.constraint.Course5;
import cn.icexmoon.entity.constraint.Student5;
import cn.icexmoon.entity.constraint.StudentCourse5;
import cn.icexmoon.entity.dbmap.Author5;
import cn.icexmoon.entity.dbmap.Book2;
import cn.icexmoon.entity.dbmap.Customer2;
import cn.icexmoon.entity.dbmap.Customer3;
import cn.icexmoon.entity.derivedid.*;
import cn.icexmoon.entity.embeddable.Person7;
import cn.icexmoon.entity.embeddable.Person8;
import cn.icexmoon.entity.formula.Order;
import cn.icexmoon.entity.inheritance.joined.Author3;
import cn.icexmoon.entity.inheritance.joined.Person3;
import cn.icexmoon.entity.inheritance.singletable.Author2;
import cn.icexmoon.entity.inheritance.singletable.Person2;
import cn.icexmoon.entity.joincolumn.*;
import cn.icexmoon.entity.jointable.Book3;
import cn.icexmoon.entity.jointable.Course2;
import cn.icexmoon.entity.jointable.Publisher3;
import cn.icexmoon.entity.jointable.Student2;
import cn.icexmoon.entity.primarykey.Author6;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Date;
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

    @Test
    public void testCustomer2(){
        Customer2 customer2 = new Customer2();
        customer2.setName("张三");
        customer2.setCity("上海");
        customer2.setAddress("南京路112号");
        entityManager.persist(customer2);
    }

    @Test
    public void testCustomer3(){
        Customer3 customer3 = new Customer3();
        customer3.setName("张三");
        customer3.setCity("上海");
        customer3.setAddress("南京路112号");
        customer3.setPhone("12345678901");
        customer3.setAge(18);
        entityManager.persist(customer3);
    }

    @Test
    public void testJoinTable(){
        Book2 book2 = new Book2();
        book2.setName("MySQL");
        Author5 author = new Author5();
        author.setName("张三");
        book2.setAuthor(author);
        entityManager.persist(book2);
    }

    @Test
    public void testJoinTable2(){
        Book3 book3 = new Book3();
        book3.setName("MySQL");
        Publisher3 publisher = new Publisher3();
        publisher.setName("上海出版社");
//        entityManager.persist(publisher);
        book3.setPublisher(publisher);
        entityManager.persist(book3);
    }

    @Test
    public void testJoinTable3(){
        Student2 student1 = new Student2("张三");
        Student2 student2 = new Student2("李四");
        Course2 course1 = new Course2("Java");
        Course2 course2 = new Course2("C++");
        Course2 course3 = new Course2("Python");
        student1.addCourse(course1,true);
        student1.addCourse(course2,true);
        student2.addCourse(course2,true);
        student2.addCourse(course3,true);
        entityManager.persist(student1);
        entityManager.persist(student2);
    }

    @Test
    public void testJoinColumn(){
        ClassRoom3 classRoom1 = new ClassRoom3("1-1");
        ClassRoom3 classRoom2 = new ClassRoom3("1-2");
        Student3 student1 = new Student3("张三");
        Student3 student2 = new Student3("李四");
        Student3 student3 = new Student3("王五");
        classRoom1.addStudent(student1,true);
        classRoom1.addStudent(student2,true);
        classRoom2.addStudent(student3,true);
        entityManager.persist(classRoom1);
        entityManager.persist(classRoom2);
    }

    @Test
    public void testColumn(){
        Book4 book4 = new Book4();
        book4.setName("MySQL");
        book4.setISBN("1000001");
        book4.setDesc("MySQL数据库");
        book4.setPrice(new BigDecimal("25.62"));
        entityManager.persist(book4);
    }

    @Test
    public void testJoinColumn2(){
        Book5 book5 = new Book5();
        book5.setName("MySQL");
        book5.setISBN("1000001");
        book5.setAuthor("张三");
        book5.setPrice(new BigDecimal("25.62"));
        entityManager.persist(book5);
        BookWindow5 bookWindow5 = new BookWindow5();
        bookWindow5.setBook(book5);
        entityManager.persist(bookWindow5);
    }

    @Test
    public void testJoinColumn3(){
        Book5 book = new Book5();
        book.setName("MySQL");
        book.setISBN("1000002");
        book.setAuthor("张三");
        book.setPrice(new BigDecimal("25.62"));
        entityManager.persist(book);
        BookHistory5 bookHistory = new BookHistory5();
        bookHistory.setBookHistoryId(new BookHistory5.BookHistoryId(book.getId(),1));
        bookHistory.setPrice(new BigDecimal("25.62"));
        bookHistory.setCreateTime(new Date());
        entityManager.persist(bookHistory);
        BookHistoryDetail5 bookHistoryDetail = new BookHistoryDetail5();
        bookHistoryDetail.setBookHistory(bookHistory);
        bookHistoryDetail.setModifyReason("初始化");
        entityManager.persist(bookHistoryDetail);
    }

    @Test
    public void testPrimaryKey(){
        Author6 author = new Author6();
        author.setName("张三");
        author.setPenName("张三丰");
        entityManager.persist(author);
    }

    @Test
    public void testColumnLength(){
        Book6 book  = new Book6();
        book.setName("MySQL");
        book.setAuthor("张三");
        book.setDesc("MySQL数据库");
        book.setDetailDesc("MySQL数据库详细描述");
        entityManager.persist(book);
    }

    @Test
    public void testColumnLength2(){
        Book7 book  = new Book7();
        book.setName("MySQL");
        book.setAuthor("张三");
        book.setDesc("MySQL数据库");
        book.setDetailDesc("MySQL数据库详细描述");
        entityManager.persist(book);
    }

    @Test
    public void testColumnLength3(){
        Book8 book  = new Book8();
        book.setName("MySQL");
        book.setAuthor("张三");
        book.setDesc("MySQL数据库");
        book.setDetailDesc("MySQL数据库详细描述");
        // 从文本文件读取内容到字符串
        String content = FileUtil.readString("D:\\download\\scm-2025-12-09.log", StandardCharsets.UTF_8);
        book.setLongText(content);
        book.setFile(FileUtil.readBytes("D:\\download\\新建文件夹.zip"));
        entityManager.persist(book);
    }

    @Test
    public void testColumnLength5(){
        Book8 book8 = entityManager.find(Book8.class, 1L);
        String longText = book8.getLongText();
        // 逐行打印 longText
        for (String line : longText.split("\n")) {
            System.out.println(line);
        }
        System.out.println(book8.getFile().length);
        // 另存文件到 D:\\download\\新建文件夹.zip
        FileUtil.writeBytes(book8.getFile(), "D:\\download\\新建文件夹2.zip");
    }

    @Test
    public void testColumnLength6(){
        Book9 book  = new Book9();
        book.setName("MySQL");
        book.setAuthor("张三");
        book.setDesc("MySQL数据库");
        book.setDetailDesc("MySQL数据库详细描述");
        LobHelper lobHelper = Hibernate.getLobHelper();
        // 从文本文件读取内容到字符串
        String content = FileUtil.readString("D:\\download\\scm-2025-12-09.log", StandardCharsets.UTF_8);
        book.setLongText(lobHelper.createClob(content));
        byte[] bytes = FileUtil.readBytes("D:\\download\\新建文件夹.zip");
        book.setFile(lobHelper.createBlob(bytes));
        entityManager.persist(book);
    }

    @Test
    public void testColumnLength7() throws SQLException {
        Book9 book = entityManager.find(Book9.class, 1L);
        String longText = book.getLongText().getSubString(1L, (int) book.getLongText().length());
        // 逐行打印 longText
        for (String line : longText.split("\n")) {
            System.out.println(line);
        }
        // 另存文件到 D:\\download\\新建文件夹.zip
        InputStream binaryStream = book.getFile().getBinaryStream();
        FileUtil.writeFromStream(binaryStream, "D:\\download\\新建文件夹3.zip");
    }

    @Test
    public void testEmbeddable(){
        Person7 person = new Person7();
        person.setName(new Person7.Name("Bruce", "Lee"));
        entityManager.persist(person);
    }

    @Test
    public void testEmbeddable2(){
        Person8 person = new Person8();
        person.setName(new Person8.Name("Bruce", "Lee"));
        entityManager.persist(person);
    }

    @Test
    public void testFormula(){
        Order order = new Order();
        order.setTotal(new BigDecimal("100.00"));
        order.setTaxRate(new BigDecimal("0.05"));
        entityManager.persist(order);
    }

    @Test
    public void testFormula2(){
        Order order = entityManager.find(Order.class, 1L);
        System.out.println(order);
    }

    @Test
    public void testDerivedId(){
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName("历史");
        entityManager.persist(bookCategory);
        BookSubCategory bookSubCategory = new BookSubCategory();
        bookSubCategory.setBookCategory(bookCategory);
        bookSubCategory.setName("近代史");
        entityManager.persist(bookSubCategory);
        BookSubCategory bookSubCategory2 = new BookSubCategory();
        bookSubCategory2.setBookCategory(bookCategory);
        bookSubCategory2.setName("外国史");
        entityManager.persist(bookSubCategory2);
    }

    @Test
    public void testDerivedId2(){
        BookCategory2 bookCategory = new BookCategory2();
        bookCategory.setName("历史");
        entityManager.persist(bookCategory);
        BookSubCategory2 bookSubCategory = new BookSubCategory2();
        bookSubCategory.setBookCategory(bookCategory);
        bookSubCategory.setName("近代史");
        entityManager.persist(bookSubCategory);
        BookSubCategory2 bookSubCategory2 = new BookSubCategory2();
        bookSubCategory2.setBookCategory(bookCategory);
        bookSubCategory2.setName("外国史");
        entityManager.persist(bookSubCategory2);
    }

    @Test
    public void testDerivedId3(){
        BookCategory3 bookCategory = new BookCategory3();
        bookCategory.setName("历史");
        entityManager.persist(bookCategory);
        BookSubCategory3 bookSubCategory = new BookSubCategory3();
        bookSubCategory.setBookCategory(bookCategory);
        bookSubCategory.setName("近代史");
        entityManager.persist(bookSubCategory);
        BookSubCategory3 bookSubCategory2 = new BookSubCategory3();
        bookSubCategory2.setBookCategory(bookCategory);
        bookSubCategory2.setName("外国史");
        entityManager.persist(bookSubCategory2);
    }

    @Test
    public void testConstraint(){
        Student5 student = new Student5();
        student.setNumber("20250001");
        student.setName("张三");
        student.setAge(18);
        entityManager.persist(student);
        Course5 course = new Course5();
        course.setName("Java");
        entityManager.persist(course);
        StudentCourse5 studentCourse = new StudentCourse5();
        studentCourse.setStudentId(student.getId());
        studentCourse.setCourseId(course.getId());
        studentCourse.setCreateTime(new Date());
        entityManager.persist(studentCourse);
    }
}
