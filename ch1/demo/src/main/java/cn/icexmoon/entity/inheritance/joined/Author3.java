package cn.icexmoon.entity.inheritance.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Author3
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/31 上午9:09
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "author_v3")
public class Author3 extends Person3{
    private String penName;
}
