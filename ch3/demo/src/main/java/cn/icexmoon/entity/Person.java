package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Person
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午7:19
 * @Version 1.0
 */
@Table(name = "tb_person")
@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }
}
