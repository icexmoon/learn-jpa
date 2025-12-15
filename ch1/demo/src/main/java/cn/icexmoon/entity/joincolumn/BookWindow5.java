package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Window
 * @Description 书籍展示位
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午9:51
 * @Version 1.0
 */
@Entity
@Table(name = "book5_window")
@Data
public class BookWindow5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", foreignKey = @ForeignKey(name = "fk_book_window_book"))
    private Book5 book;
}
