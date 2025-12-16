package com.example.demo.entity.cascade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * @ClassName OrderItem2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午2:22
 * @Version 1.0
 */
@Entity
@Table(name = "order_item2")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @Min(0)
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Min(0)
    @NotNull
    private Double amount;
    @Min(0)
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order2 order;

    public OrderItem2(@NonNull String name, @NonNull BigDecimal price, @NonNull Double amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.total = price.multiply(new BigDecimal(amount));
    }
}
