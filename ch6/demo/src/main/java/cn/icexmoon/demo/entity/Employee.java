package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Employee
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:01
 * @Version 1.0
 */
@Table(name = "tb_employee")
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String email;
    @Column(columnDefinition = "tinyint unsigned")
    private Integer age;
}
