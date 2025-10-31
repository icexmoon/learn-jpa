package cn.icexmoon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Child
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午2:14
 * @Version 1.0
 */
@Table(name = "child")
@Entity
@Data
@NoArgsConstructor
public class ChildEntity extends RootEntity {
    private String name;
    public ChildEntity(String name) {
        this.name = name;
    }
}
