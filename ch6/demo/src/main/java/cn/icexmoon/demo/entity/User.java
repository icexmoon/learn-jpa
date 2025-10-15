package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName User
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/9 上午10:55
 * @Version 1.0
 */
@Table(name = "tb_user")
@Entity
@Data
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "tinyint unsigned")
    private Integer age;
    @Column(length = 5)
    private String ageRange;
    @Column(length = 20, nullable = false, unique = true)
    private String name;
    @Column(length = 6)
    private String regionCode;
    @Column(columnDefinition = "tinyint unsigned")
    private Integer level;

    public User(Long id, String name, String regionCode, Integer level) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.level = level;
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}
