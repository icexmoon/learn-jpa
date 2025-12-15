package cn.icexmoon.entity.derivedid;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName BookCategory
 * @Description 书籍分类
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午5:34
 * @Version 1.0
 */
@Entity
@Table(name = "book_category")
@Data
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
