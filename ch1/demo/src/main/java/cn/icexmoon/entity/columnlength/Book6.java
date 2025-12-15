package cn.icexmoon.entity.columnlength;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Book6
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午2:24
 * @Version 1.0
 */
@Entity
@Table(name = "book6")
@Data
public class Book6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(length = 10)
    private String author;
    @Column(name = "book_desc", length = 200)
    private String desc;
    @Column(name = "detail_desc", length = 2000)
    private String detailDesc;
}
