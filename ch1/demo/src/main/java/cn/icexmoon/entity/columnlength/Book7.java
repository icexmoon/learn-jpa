package cn.icexmoon.entity.columnlength;

import jakarta.persistence.*;
import lombok.Data;

import static org.hibernate.Length.DEFAULT;
import static org.hibernate.Length.LONG;

/**
 * @ClassName Book6
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午2:24
 * @Version 1.0
 */
@Entity
@Table(name = "book7")
@Data
public class Book7 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = DEFAULT)
    private String name;
    @Column(length = DEFAULT)
    private String author;
    @Column(name = "book_desc", length = DEFAULT)
    private String desc;
    @Column(name = "detail_desc", length = LONG)
    private String detailDesc;
}
