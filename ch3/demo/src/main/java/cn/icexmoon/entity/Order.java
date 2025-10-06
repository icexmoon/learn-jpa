package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Order
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午5:15
 * @Version 1.0
 */
@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_name", length = 20, nullable = false)
    private String orderName;
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
        this.customer = customer;
    }
}
