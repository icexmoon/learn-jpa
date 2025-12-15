package cn.icexmoon.entity.dbmap;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Author2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/11 上午8:47
 * @Version 1.0
 */
@Entity
@Table(name = "author5")
@Data
public class Author5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book2> books;
}
