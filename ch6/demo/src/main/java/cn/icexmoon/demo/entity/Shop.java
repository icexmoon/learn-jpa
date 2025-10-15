package cn.icexmoon.demo.entity;

import cn.icexmoon.demo.util.idgenerator.CustomerIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Shop
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:24
 * @Version 1.0
 */
@Table(name = "tb_shop")
@Entity
@Data
@NoArgsConstructor
public class Shop {
    @Id
    @Column(columnDefinition = "char(19)")
    @CustomerIdGenerator
    private String id;

    @Column(length = 20)
    private String name;

    public Shop(String name) {
        this.name = name;
    }
}
