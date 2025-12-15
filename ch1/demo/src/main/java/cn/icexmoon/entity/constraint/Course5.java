package cn.icexmoon.entity.constraint;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @ClassName Course5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午8:56
 * @Version 1.0
 */
@Entity
@Table(name = "course5")
@Data
public class Course5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;
}
