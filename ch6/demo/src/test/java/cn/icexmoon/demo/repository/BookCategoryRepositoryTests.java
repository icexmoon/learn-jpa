package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.BookCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName BookCategoryRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:04
 * @Version 1.0
 */
@SpringBootTest
public class BookCategoryRepositoryTests {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Test
    public void testAddBookCategory() {
        BookCategory bookCategory1 = new BookCategory("艺术");
        BookCategory bookCategory2 = new BookCategory("历史");
        BookCategory bookCategory3 = new BookCategory("小说");
        List<BookCategory> books = List.of(bookCategory1, bookCategory2, bookCategory3);
        bookCategoryRepository.saveAll(books);
    }
}
