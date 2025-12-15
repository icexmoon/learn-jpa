package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @ClassName Book5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午9:39
 * @Version 1.0
 */
@Entity
@Table(name = "book5")
@Data
public class Book5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Length(max = 10)
    @NotBlank
    @Column(name = "isbn", unique = true)
    @NaturalId
    private String ISBN;

    @NotBlank
    @Length(max = 20)
    private String author;

    @NotNull
    @Min(0)
    @Column(scale = 2, precision = 10)
    private BigDecimal price;
}
