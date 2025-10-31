package cn.icexmoon.entity.inheritance.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Author2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/31 上午8:47
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("A")
public class Author2 extends Person2 {
    private String penName;
}
