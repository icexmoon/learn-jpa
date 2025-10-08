package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Blog;
import cn.icexmoon.demo.repository.provider.BlogSqlProvider;
import com.blinkfox.fenix.jpa.QueryFenix;
import com.blinkfox.fenix.specification.FenixJpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName BlogRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 下午2:17
 * @Version 1.0
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, FenixJpaSpecificationExecutor<Blog> {
    @QueryFenix(countQuery = "queryBlogsTotal")
    Page<Blog> queryBlogs(@Param("blog") Blog blog, Pageable pageable);

    @QueryFenix(provider = BlogSqlProvider.class)
    List<Blog> queryBlogsWithProvider(@Param("blog") Blog blog);
}
