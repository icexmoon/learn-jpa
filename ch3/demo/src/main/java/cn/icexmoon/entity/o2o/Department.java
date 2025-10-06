package cn.icexmoon.entity.o2o;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Department
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午7:46
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "tb_department")
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "department")
    @ToString.Exclude
    private Manager manager;

    public Department(String name) {
        this.name = name;
    }
}
