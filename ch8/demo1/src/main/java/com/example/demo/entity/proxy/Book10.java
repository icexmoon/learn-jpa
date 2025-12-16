package com.example.demo.entity.proxy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @ClassName Book10
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午3:08
 * @Version 1.0
 */
@Entity
@Table(name = "book10")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "publishers")
public class Book10 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 50)
    private String name;

    @NaturalId
    @NotBlank
    @Length(max = 20)
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String isbn;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher10",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private List<Publisher10> publishers;

    public void addPublisher(Publisher10 publisher, boolean bidirectional){
        if (publishers == null){
            publishers = new java.util.ArrayList<>();
        }
        publishers.add(publisher);
        if (bidirectional){
            publisher.addBook(this, false);
        }
    }
}
