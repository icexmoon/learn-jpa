package com.example.demo.entity.cascade;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Order2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午2:21
 * @Version 1.0
 */
@Entity
@Table(name = "order2")
@Data
public class Order2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
        mappedBy = "order",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},
        orphanRemoval = true
    )
    private List<OrderItem2> orderItems;

    public void addOrderItem(OrderItem2 orderItem){
        if (orderItems == null){
            orderItems = new java.util.ArrayList<>();
        }
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
