package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Region
 * @Description 省市区编码
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午10:02
 * @Version 1.0
 */
@Data
@Table(name = "tb_region")
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 6)
    private String code;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private Integer level;
}
