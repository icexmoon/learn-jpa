package cn.icexmoon.demo.entity;

import cn.icexmoon.demo.util.idgenerator.SnowflakeIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Book
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:30
 * @Version 1.0
 */
@Table(name = "tb_book")
@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @SnowflakeIdGenerator
//    @GeneratedValue(generator = "snowflake")
//    @GenericGenerator(name = "snowflake", type = SnowflakeIdGenerator.class)
    private Long id;
    @Column(length = 20)
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
