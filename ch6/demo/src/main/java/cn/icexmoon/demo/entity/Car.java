package cn.icexmoon.demo.entity;

import cn.icexmoon.demo.util.idgenerator.NanoIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Car
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:15
 * @Version 1.0
 */
@Table(name = "tb_car")
@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @NanoIdGenerator
    @Column(columnDefinition = "char(21)")
    private String id;
    private String brand;

    public Car(String brand) {
        this.brand = brand;
    }
}
