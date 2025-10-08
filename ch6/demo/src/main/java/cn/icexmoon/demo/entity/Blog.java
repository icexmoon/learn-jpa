package cn.icexmoon.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Blog
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 下午2:13
 * @Version 1.0
 */
@Table(name = "tb_blog")
@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String title;
    private String content;
    @Column(length = 10)
    private String author;
    private Date createTime;
    private Date updateTime;
}
