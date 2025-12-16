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
 * @ClassName Publisher
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 下午3:08
 * @Version 1.0
 */
@Entity
@Table(name = "publisher10")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "books")
public class Publisher10 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 50)
    @Column(unique = true)
    @NaturalId
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(mappedBy = "publishers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Book10> books;

    public void addBook(Book10 book, boolean bidirectional){
        if (books == null){
            books = new java.util.ArrayList<>();
        }
        books.add(book);
        if (bidirectional){
            book.addPublisher(this, false);
        }
    }
}
