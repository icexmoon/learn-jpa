package cn.icexmoon.entity.embeddable;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Struct;

/**
 * @ClassName Person7
 * @Description MySQL 不支持自定义类型（UDT）
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午3:46
 * @Version 1.0
 */
@Entity
@Table(name = "person7")
@Data
public class Person7 {
    @Struct(name = "name")
    @Embeddable
    public record Name(
            @Column(name = "first_name")
            String firstName,
            @Column(name = "last_name")
            String lastName) {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Name name;
}
