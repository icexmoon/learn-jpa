package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName EmployeeRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:08
 * @Version 1.0
 */
@SpringBootTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setName("Tom");
        employee.setAge(18);
        employee.setEmail("tom@qq.com");
        employeeRepository.save(employee);
    }

    @Test
    public void testQueryEmployees() {
        Employee employee = new Employee();
        employee.setName("Tom");
        List<Employee> employees = employeeRepository.queryEmployees(employee);
        System.out.println(employees);
    }

    @Test
    public void testQueryEmployees2() {
        Employee employee = new Employee();
        List<Employee> employees = employeeRepository.queryEmployees(employee);
        System.out.println(employees);
    }
}
