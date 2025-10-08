package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @ClassName Person
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午6:17
 * @Version 1.0
 */
@Entity
@Table(name = "tb_person")
@Data
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 10)
    private String lastName;
    private String email;
    private LocalDate birth;
}
