package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BookHistory
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午10:16
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "book5_history")
public class BookHistory5 {
    @Embeddable
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BookHistoryId {
        @Column(name = "book_id")
        private Long bookId;
        @Column(name = "version")
        private Integer version;

        public BookHistoryId(Long bookId, Integer version) {
            this.bookId = bookId;
            this.version = version;
        }
    }

    @EmbeddedId
    private BookHistoryId bookHistoryId;
    @NotNull
    @Min(0)
    @Column(scale = 2, precision = 10, nullable = false)
    private BigDecimal price;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;
}
