package cn.icexmoon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @ClassName ClassRoom
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午5:03
 * @Version 1.0
 */
@Table(name = "school")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class School extends BaseEntity{
    private String name;

    public School(String name) {
        this.name = name;
    }
}
