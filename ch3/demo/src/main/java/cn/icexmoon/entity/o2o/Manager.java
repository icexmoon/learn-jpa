package cn.icexmoon.entity.o2o;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Manager
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午7:46
 * @Version 1.0
 */
@Table(name = "tb_manager")
@Entity
@Data
@NoArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JoinColumn(name = "department_id", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Department department;

    public Manager(String name) {
        this.name = name;
    }
}
