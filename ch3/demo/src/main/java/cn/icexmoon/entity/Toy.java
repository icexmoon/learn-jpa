package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Toy
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午6:45
 * @Version 1.0
 */
@Entity
@Table(name = "tb_toy")
@Data
@NoArgsConstructor
@ToString
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Toy(String name) {
        this.name = name;
    }
}
