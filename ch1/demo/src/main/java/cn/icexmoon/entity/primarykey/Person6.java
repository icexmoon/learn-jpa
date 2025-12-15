package cn.icexmoon.entity.primarykey;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName Person5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午11:10
 * @Version 1.0
 */
@Entity
@Table(name = "person6")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(max = 20)
    @Column(nullable = false, length = 20)
    private String name;
    @Length(max = 50)
    @Column(length = 50)
    private String email;
}
