package cn.icexmoon;

import cn.icexmoon.entity.Customer;
import cn.icexmoon.entity.Order;
import cn.icexmoon.entity.Person;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.AvailableHints;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @ClassName JpqlTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午4:04
 * @Version 1.0
 */
public class JpqlTests extends EntityTestsBase {
    @Test
    public void testQuery() {
        String jpql = "SELECT c FROM Customer c WHERE c.age>?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 10);
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testQuery2() {
        String jpql = "SELECT c FROM Customer c WHERE c.age>:age";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("age", 10);
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testQuery3() {
        String jpql = "FROM Customer c WHERE c.age>:age";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("age", 10);
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testQuery4() {
        String jpql = "SELECT c.age,c.lastName FROM Customer c WHERE c.age>:age";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("age", 10);
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testQuery5() {
        String jpql = "SELECT new Customer(c.lastName,c.age) FROM Customer c WHERE c.age>:age";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("age", 10);
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }


    @Test
    public void testNamedQuery() {
        Query query = entityManager.createNamedQuery("Customer.findByLastName");
        query.setParameter("lastName", "张三");
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testNativeQuery() {
        Query query = entityManager.createNativeQuery("SELECT * FROM tb_customer");
        List<?> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testQueryCache() {
        Query query = entityManager.createNativeQuery("SELECT * FROM tb_customer");
        List<?> customers = query.getResultList();
        System.out.println(customers);
        Query query2 = entityManager.createNativeQuery("SELECT * FROM tb_customer");
        List<?> customers2 = query2.getResultList();
        System.out.println(customers2);
    }

    @Test
    public void testQueryCache2() {
        Query query = entityManager.createNativeQuery("SELECT * FROM tb_customer")
                .setHint(AvailableHints.HINT_CACHEABLE, true);
        List<?> customers = query.getResultList();
        System.out.println(customers);
        Query query2 = entityManager.createNativeQuery("SELECT * FROM tb_customer")
                .setHint(AvailableHints.HINT_CACHEABLE, true);
        ;
        List<?> customers2 = query2.getResultList();
        System.out.println(customers2);
    }

    @Test
    public void testGroupBy() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT o.customer FROM Order o " +
                "GROUP BY o.customer HAVING count(o.customer)>=3", Customer.class);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testLeftJoin() {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p left outer join fetch p.cars where p.id=:id", Person.class);
        query.setParameter("id", 1L);
        List<Person> persons = query.getResultList();
        System.out.println(persons);
    }

    @Test
    public void testLeftJoin2() {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.id=:id", Person.class);
        query.setParameter("id", 1L);
        List<Person> persons = query.getResultList();
        System.out.println(persons);
    }

    @Test
    public void testChildQuery() {
        TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.customer in ((select c from Customer c where c.age>:age))", Order.class);
        query.setParameter("age", 20);
        List<Order> orders = query.getResultList();
        System.out.println(orders);
    }

    @Test
    public void testFunc() {
        TypedQuery<String> query = entityManager.createQuery("select concat(c.lastName,'(',c.birth,')') from Customer c", String.class);
        List<String> names = query.getResultList();
        System.out.println(names);
    }

    @Test
    public void testUpdate() {
        entityManager.createQuery("update Customer c set c.lastName=:lastName where c.id=:id")
                .setParameter("id", 1L)
                .setParameter("lastName", "张三丰")
                .executeUpdate();
    }
}
