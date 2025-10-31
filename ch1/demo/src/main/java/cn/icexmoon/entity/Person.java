package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * @ClassName Person
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午2:42
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person {
    @Embeddable
    public record Name(String firstName, String lastName) {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    private Name name;
    private Integer age;
    public Person(Name name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
