package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName BookCategoryRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:04
 * @Version 1.0
 */
public interface BookCategoryRepository extends JpaRepository<BookCategory, String> {
}
