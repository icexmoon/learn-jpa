package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Car
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午7:20
 * @Version 1.0
 */
@Table(name = "tb_car")
@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branch;
    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person owner;

    public Car(String branch) {
        this.branch = branch;
    }
}
