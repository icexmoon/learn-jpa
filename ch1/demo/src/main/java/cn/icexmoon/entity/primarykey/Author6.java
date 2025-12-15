package cn.icexmoon.entity.primarykey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName Author5
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 上午11:10
 * @Version 1.0
 */
@Entity
@Table(name = "author6")
@Data
@PrimaryKeyJoinColumn(name = "person_id")
public class Author6 extends Person6 {
    @NotBlank
    @Length(max = 20)
    @Column(length = 20, nullable = false)
    private String penName;
}
