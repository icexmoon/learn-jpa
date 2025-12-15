package cn.icexmoon.entity.constraint;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName Student5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午8:55
 * @Version 1.0
 */
@Entity
@Table(name = "student5", check = @CheckConstraint(
        name = "name_check", constraint = "name IS NOT NULL and length(name)>0 and length(name)<20"))
@Data
public class Student5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "student_number", unique = true, nullable = false)
    private String number;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Min(0)
    @Max(120)
    @NotNull
    @Column(nullable = false, check = @CheckConstraint(name = "age_check", constraint = "age >= 0 AND age <= 120"))
    private Integer age;
}
