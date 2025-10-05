package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Customer
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/3 下午7:40
 * @Version 1.0
 */
@Entity
@Table(name = "customer")
@Data
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name", length = 10, nullable = false)
    private String lastName;
    private Integer age;
    @Transient
    private Integer score;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private Date createTime;
}
