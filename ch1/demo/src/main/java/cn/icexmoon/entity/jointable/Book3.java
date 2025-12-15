package cn.icexmoon.entity.jointable;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Book
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 上午9:01
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "book3")
public class Book3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher3", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Publisher3 publisher;
}
