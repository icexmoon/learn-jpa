package cn.icexmoon.demo.repository;

import cn.hutool.core.util.RandomUtil;
import cn.icexmoon.demo.entity.Blog;
import cn.icexmoon.demo.repository.param.BlogParam;
import com.blinkfox.fenix.helper.StringHelper;
import com.blinkfox.fenix.specification.predicate.FenixPredicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

/**
 * @ClassName BlogRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 下午2:23
 * @Version 1.0
 */
@SpringBootTest
public class BlogRepositoryTests {
    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void testQueryBlogs() {
        final int PAGE_NUM = 1;
        final int PAGE_SIZE = 5;
        Pageable pageable = PageRequest.of(PAGE_NUM - 1, PAGE_SIZE);
        Blog blog = new Blog();
        blog.setTitle("a");
//        blog.setAuthor("DD");
//        blog.setCreateTime(DateUtil.parse("2025-7-01 00:00:00"));
//        blog.setUpdateTime(DateUtil.parse("2025-8-31 23:59:59"));
        Set<Long> ids = new HashSet<>(Set.of(1L, 2L, 3L));
        Page<Blog> blogPage = blogRepository.queryBlogs(ids, blog, pageable);
        System.out.println("总行数：" + blogPage.getTotalElements());
        System.out.println("总页数：" + blogPage.getTotalPages());
        System.out.println("当前页数据：" + blogPage.getContent());
        System.out.println("当前页：" + (blogPage.getNumber() + 1));
        System.out.println("每页大小：" + blogPage.getSize());
        System.out.println("是否有下一页：" + blogPage.hasNext());
        System.out.println("是否有上一页：" + blogPage.hasPrevious());
    }

    @Test
    public void testAddBlogs() {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Blog blog = new Blog();
            blog.setTitle(RandomUtil.randomString(10));
            blog.setContent(RandomUtil.randomString(20));
            blog.setAuthor(RandomUtil.randomString(5));
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blogs.add(blog);
        }
        blogRepository.saveAll(blogs);
    }

    @Test
    public void testQueryBlogsWithProvider() {
        Blog blog = new Blog();
        blog.setTitle("NuS");
        List<Blog> blogs = blogRepository.queryBlogsWithProvider(blog);
        System.out.println(blogs);
    }

    @Test
    public void testFenixSpecification() {
        final int PAGE_NUM = 1;
        final int PAGE_SIZE = 5;
        Blog blog = new Blog();
        blog.setTitle("NuS");
        Pageable pageable = PageRequest.of(PAGE_NUM - 1, PAGE_SIZE);
        FenixPredicate specification = (builder) -> {
            return builder
                    .andLike("title", blog.getTitle())
                    .andLike("author", blog.getAuthor(), StringHelper.isNotBlank(blog.getAuthor()))
                    .andBetween("createTime", blog.getCreateTime(), blog.getUpdateTime(), blog.getCreateTime() != null || blog.getUpdateTime() != null)
                    .build();
        };
        Page<Blog> blogPage = blogRepository.findAll(specification, pageable);
        System.out.println("总行数：" + blogPage.getTotalElements());
        System.out.println("总页数：" + blogPage.getTotalPages());
        System.out.println("当前页数据：" + blogPage.getContent());
        System.out.println("当前页：" + (blogPage.getNumber() + 1));
        System.out.println("每页大小：" + blogPage.getSize());
        System.out.println("是否有下一页：" + blogPage.hasNext());
        System.out.println("是否有上一页：" + blogPage.hasPrevious());
    }

    @Test
    public void testQueryByBean() {
        final int PAGE_NUM = 1;
        final int PAGE_SIZE = 5;
        BlogParam blogParam = new BlogParam();
        blogParam.setTitle("NuS");
        Pageable pageable = PageRequest.of(PAGE_NUM - 1, PAGE_SIZE);
        Page<Blog> blogPage = blogRepository.findAllOfBean(blogParam, pageable);
        System.out.println("总行数：" + blogPage.getTotalElements());
        System.out.println("总页数：" + blogPage.getTotalPages());
        System.out.println("当前页数据：" + blogPage.getContent());
        System.out.println("当前页：" + (blogPage.getNumber() + 1));
        System.out.println("每页大小：" + blogPage.getSize());
        System.out.println("是否有下一页：" + blogPage.hasNext());
        System.out.println("是否有上一页：" + blogPage.hasPrevious());
    }
}
