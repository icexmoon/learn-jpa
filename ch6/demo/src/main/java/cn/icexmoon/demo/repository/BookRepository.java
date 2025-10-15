package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Book;
import com.blinkfox.fenix.jpa.FenixJpaRepository;

/**
 * @ClassName BookRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:44
 * @Version 1.0
 */
public interface BookRepository extends FenixJpaRepository<Book, Long> {
}
