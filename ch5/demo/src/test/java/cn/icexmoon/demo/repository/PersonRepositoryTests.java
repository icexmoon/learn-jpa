package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PeersonRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午6:50
 * @Version 1.0
 */
@SpringBootTest
public class PersonRepositoryTests {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testAdd() {
        Person person = new Person();
        person.setLastName("张三丰");
        person.setEmail("zsf@qq.com");
        person.setBirth(LocalDate.of(1995, 10, 7));
        personRepository.save(person);
    }

    @Test
    public void testFindByLastName() {
        Person person = personRepository.findByLastName("张三丰");
        System.out.println(person);
    }

    @Test
    public void testFindByLastNameStartingWithAndBirthBefore() {
        Person person = personRepository.findByLastNameStartsWithAndBirthBefore("张", LocalDate.of(2000, 10, 7));
        System.out.println(person);
    }

    @Test
    public void testGetMaxIdPerson() {
        Person person = personRepository.getMaxIdPerson();
        System.out.println(person);
    }

    @Test
    public void testQueryTest() {
        Person person = personRepository.queryTest("张三丰", "zsf@qq.com").get(0);
        System.out.println(person);
    }

    @Test
    public void testQueryTest2() {
        Person person = personRepository.queryTest2("zsf@qq.com", "张三丰").get(0);
        System.out.println(person);
    }

    @Test
    public void testQueryTest3() {
        List<Person> persons = personRepository.queryTest3("zsf", "张");
        System.out.println(persons);
    }

    @Test
    public void testGetTotalCount() {
        int count = personRepository.getTotalCount();
        System.out.println(count);
    }

    @Test
    public void testUpdateEmailById() {
        Assertions.assertThrowsExactly(InvalidDataAccessApiUsageException.class, () -> {
            personRepository.updateEmailById("zsf@163.com", 1L);
        });
    }

    @Test
    public void testDeleteById() {
        personRepository.deleteById(1L);
    }

    @Test
    public void testSave() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setLastName("张三丰" + i);
            person.setEmail("zsf" + i + "@qq.com");
            person.setBirth(LocalDate.of(1995, 10, 7));
            persons.add(person);
        }
        personRepository.saveAll(persons);
    }

    @Test
    public void testPagingAndSorting() {
        final int CURRENT_PAGE = 3;
        PageRequest pageable = PageRequest.of(CURRENT_PAGE - 1, 5);
        Page<Person> page = personRepository.findAll(pageable);
        System.out.println("总行数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页数据：" + page.getContent());
        System.out.println("当前页：" + (page.getNumber() + 1));
        System.out.println("每页大小：" + page.getSize());
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否第一页：" + page.isFirst());
    }

    @Test
    public void testPagingAndSorting2() {
        final int CURRENT_PAGE = 2;
        final int PAGE_SIZE = 5;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(CURRENT_PAGE - 1, PAGE_SIZE, sort);
        Page<Person> page = personRepository.findAll(pageable);
        System.out.println("总行数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页数据：" + page.getContent());
        System.out.println("当前页：" + (page.getNumber() + 1));
        System.out.println("每页大小：" + page.getSize());
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否第一页：" + page.isFirst());
    }

    @Test
    public void testPagingAndSorting3() {
        final int CURRENT_PAGE = 2;
        final int PAGE_SIZE = 5;
        Sort.Order order1 = Sort.Order.desc("id");
        Sort.Order order2 = Sort.Order.asc("lastName");
        Sort sort = Sort.by(List.of(order1, order2));
        PageRequest pageable = PageRequest.of(CURRENT_PAGE - 1, PAGE_SIZE, sort);
        Page<Person> page = personRepository.findAll(pageable);
        System.out.println("总行数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页数据：" + page.getContent());
        System.out.println("当前页：" + (page.getNumber() + 1));
        System.out.println("每页大小：" + page.getSize());
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否第一页：" + page.isFirst());
    }

    @Test
    public void testJpaRepository() {
        Person person = new Person();
        person.setLastName("Tom");
        person.setEmail("tom@qq.com");
        person.setBirth(LocalDate.of(1995, 10, 7));
        personRepository.saveAndFlush(person);
    }

    @Test
    public void testJpaRepository2() {
        Person person = new Person();
        person.setId(13L);
        person.setLastName("Jack");
        person.setEmail("tom@qq.com");
        person.setBirth(LocalDate.of(1995, 10, 7));
        Person personNew = personRepository.saveAndFlush(person);
        Assertions.assertNotSame(person, personNew);
    }

    @Test
    public void testJpaSpecificationExecutor() {
        final int PAGE_NUM = 1;
        final int PAGE_SIZE = 5;
        final String lastName = "张";
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("lastName"), "%"+lastName+"%");
            }
        };
        Page<Person> page = personRepository.findAll(specification
                , PageRequest.of(PAGE_NUM - 1, PAGE_SIZE));
        System.out.println("总行数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页数据：" + page.getContent());
        System.out.println("当前页：" + (page.getNumber() + 1));
        System.out.println("每页大小：" + page.getSize());
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否第一页：" + page.isFirst());
    }

    @Test
    public void testPageAndSearch() {
        final int PAGE_NUM = 1;
        final int PAGE_SIZE = 5;
        final String lastName = "张";
        Page<Person> page = personRepository.pageAndSearch(PAGE_NUM, PAGE_SIZE, lastName);
        System.out.println("总行数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页数据：" + page.getContent());
        System.out.println("当前页：" + (page.getNumber() + 1));
        System.out.println("每页大小：" + page.getSize());
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否第一页：" + page.isFirst());
    }
}
