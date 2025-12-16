package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Car2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午11:16
 * @Version 1.0
 */
@Entity
@Table(name = "car2")
@Data
@ToString
public class Car2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String owner;
}
