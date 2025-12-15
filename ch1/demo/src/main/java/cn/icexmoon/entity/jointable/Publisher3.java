package cn.icexmoon.entity.jointable;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Publisher3
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 上午9:01
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "publisher3")
public class Publisher3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
