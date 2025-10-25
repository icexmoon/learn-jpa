package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Student
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午3:35
 * @Version 1.0
 */
@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "tinyint unsigned")
    private Integer age;

    public Student(String name) {
        this.name = name;
    }
}
