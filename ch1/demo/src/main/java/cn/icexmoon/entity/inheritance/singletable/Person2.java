package cn.icexmoon.entity.inheritance.singletable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Person2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/31 上午8:44
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "person_v2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, name = "kind")
@DiscriminatorValue("P")
public class Person2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;
    private Integer age;
}
