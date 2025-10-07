package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@NamedQuery(name = "Customer.findByLastName", query = "select c from Customer c where c.lastName = :lastName")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name", length = 10, nullable = false)
    private String lastName;
    private Integer age;
    private LocalDate birth;
    private LocalDateTime createTime;

    public Customer(String lastName, Integer age) {
        this.lastName = lastName;
        this.age = age;
    }
}
