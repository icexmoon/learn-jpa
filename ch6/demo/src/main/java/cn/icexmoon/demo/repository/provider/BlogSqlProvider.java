package cn.icexmoon.demo.repository.provider;

import cn.icexmoon.demo.entity.Blog;
import com.blinkfox.fenix.bean.SqlInfo;
import com.blinkfox.fenix.core.Fenix;
import com.blinkfox.fenix.helper.StringHelper;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName BlogSqlProvider
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 下午2:52
 * @Version 1.0
 */
public class BlogSqlProvider {
    public SqlInfo queryBlogsWithProvider(@Param("blog") Blog blog){
        return Fenix.start()
                .select("b")
                .from("Blog").as("b")
                .where("1=1")
                .andLike("b.title", blog.getTitle(), StringHelper.isNotBlank(blog.getTitle()))
                .andLike("b.author", blog.getAuthor(), StringHelper.isNotBlank(blog.getAuthor()))
                .andBetween("b.createTime", blog.getCreateTime(), blog.getUpdateTime())
                .end();
    }
}
