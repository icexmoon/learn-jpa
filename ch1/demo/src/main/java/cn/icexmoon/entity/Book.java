package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName Book
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午4:20
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(columnDefinition = "decimal(10,2)")
    private BigDecimal price;
    @Version
    @Column(columnDefinition = "int unsigned")
    private Integer version;

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
