package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Child
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午6:43
 * @Version 1.0
 */
@Entity
@Table(name = "tb_child")
@Data
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JoinColumn(name = "child_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Toy> toys = new ArrayList<>();

    public Child(String name) {
        this.name = name;
    }
}
