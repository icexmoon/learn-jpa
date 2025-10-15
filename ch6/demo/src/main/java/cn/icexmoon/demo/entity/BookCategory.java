package cn.icexmoon.demo.entity;

import cn.icexmoon.demo.util.idgenerator.SnowflakeStrIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BookCategory
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:59
 * @Version 1.0
 */
@Table(name = "tb_book_category")
@Entity
@Data
@NoArgsConstructor
public class BookCategory {
    @Id
    @SnowflakeStrIdGenerator
    @Column(columnDefinition = "char(9)")
    private String id;
    @Column(length = 20)
    private String name;

    public BookCategory(String name) {
        this.name = name;
    }
}
