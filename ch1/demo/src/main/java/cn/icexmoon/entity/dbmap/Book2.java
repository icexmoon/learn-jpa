package cn.icexmoon.entity.dbmap;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Book2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/11 上午8:47
 * @Version 1.0
 */
@Entity
@Table(name = "book2")
@Data
public class Book2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author5 author;
}
