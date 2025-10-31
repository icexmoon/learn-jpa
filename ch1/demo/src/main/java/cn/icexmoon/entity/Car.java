package cn.icexmoon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

/**
 * @ClassName Car
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午3:23
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class Car {
    public enum Color{
        Yellow(1),Red(2),Blue(3);
        @EnumeratedValue
        final int value;
        Color(int value){
            this.value = value;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    @NaturalId
    @NotNull
    private String engineCode;
    @Enumerated
    private Color color;

    public Car(String brand, String engineCode) {
        this.brand = brand;
        this.engineCode = engineCode;
    }
}
