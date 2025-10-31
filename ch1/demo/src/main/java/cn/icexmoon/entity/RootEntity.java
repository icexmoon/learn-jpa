package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午5:01
 * @Version 1.0
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Date createTime;
    @Column(length = 10)
    private String createUser;
    private Date updateTime;
    @Column(length = 10)
    private String updateUser;
}
