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
@Table(name = "book_sub_category3")
@Data
public class BookSubCategory3 {
    @Embeddable
    public record BookSubCategoryId(
            @GeneratedValue(strategy = GenerationType.UUID)
            @Column(name = "id")
            String id,
            @Column(name = "book_category_id")
            Long bookCategoryId) {
    }

    @EmbeddedId
    private BookSubCategoryId id;

    @MapsId("bookCategoryId")
    @ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory3 bookCategory;
    private String name;
}
