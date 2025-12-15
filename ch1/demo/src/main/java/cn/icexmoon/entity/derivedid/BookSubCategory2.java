package cn.icexmoon.entity.derivedid;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName BookCategorySub
 * @Description 书籍二级分类
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午5:35
 * @Version 1.0
 */
@Entity
@Table(name = "book_sub_category2")
@Data
@IdClass(BookSubCategory2.BookSubCategoryId.class)
public class BookSubCategory2 {
    public record BookSubCategoryId(String id, Long bookCategoryId) {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Id
    private Long bookCategoryId;

    @MapsId("bookCategoryId")
    @ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory2 bookCategory;
    private String name;
}
