package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName BookHistoryDetail5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午10:23
 * @Version 1.0
 */
@Entity
@Table(name = "book5_history_detail")
@Data
public class BookHistoryDetail5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumns(value = {
        @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
        @JoinColumn(name = "version", referencedColumnName = "version")
    }, foreignKey = @ForeignKey(name = "fk_book_history_detail_book_history"))
    private BookHistory5 bookHistory;
    @NotNull
    @Length(max = 500)
    @Column(name = "modify_reason", nullable = false, length = 500)
    private String modifyReason;
}
