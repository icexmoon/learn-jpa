package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName Customer
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/3 下午7:40
 * @Version 1.0
 */
@Entity
@Table(name = "tb_customer")
@Data
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name", length = 10, nullable = false)
    private String lastName;
    private Integer age;
    private LocalDate birth;
    private LocalDateTime createTime;
}
