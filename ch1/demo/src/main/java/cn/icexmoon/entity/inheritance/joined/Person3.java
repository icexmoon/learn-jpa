package cn.icexmoon.entity.inheritance.joined;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Person3
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/31 上午9:09
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "person_v3")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;
    private Integer age;
}
