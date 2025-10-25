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
@MappedSuperclass
@EntityListeners(BaseEntity.BaseEntityEvents.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    @Column(length = 10)
    private String createUser;
    private Date updateTime;
    @Column(length = 10)
    private String updateUser;
    public static class BaseEntityEvents {
        @PrePersist
        public void prePersist(BaseEntity baseEntity) {
            baseEntity.setCreateTime(new Date());
            baseEntity.setUpdateTime(new Date());
        }

        @PreUpdate
        public void preUpdate(BaseEntity baseEntity) {
            baseEntity.setUpdateTime(new Date());
        }
    }
}
