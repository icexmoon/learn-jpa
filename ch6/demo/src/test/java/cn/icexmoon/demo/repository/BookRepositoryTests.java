package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName BookRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:43
 * @Version 1.0
 */
@SpringBootTest
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAdd(){
        Book book1 = new Book("哈利波特与魔法石");
        Book book2 = new Book("哈利波特与密室");
        Book book3 = new Book("哈利波特与阿兹卡班的囚徒");
        List<Book> books = Arrays.asList(book1, book2, book3);
        bookRepository.saveAll(books);
    }
}
