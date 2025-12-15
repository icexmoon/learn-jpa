package cn.icexmoon.entity.column;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

import static org.hibernate.Length.LONG32;

/**
 * @ClassName Book4
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午8:53
 * @Version 1.0
 */
@Entity
@Table(name = "book4")
@SecondaryTable(name = "book4_detail", pkJoinColumns = @jakarta.persistence.PrimaryKeyJoinColumn(name = "id"))
@Data
public class Book4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String ISBN;

    @Column(name = "book_desc", table = "book4_detail", length = LONG32)
    private String desc;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 10)
    private BigDecimal price;
}
