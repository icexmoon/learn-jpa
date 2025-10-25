package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ClassItem
 * @Description 课程
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午3:32
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class ClassItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 100)
    private String description;

    public ClassItem(String name) {
        this.name = name;
    }
}
