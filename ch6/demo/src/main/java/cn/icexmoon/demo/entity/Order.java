package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Order
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午11:17
 * @Version 1.0
 */
@Entity
@Table(name = "tb_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, length = 6)
    private String regionCode;
}
