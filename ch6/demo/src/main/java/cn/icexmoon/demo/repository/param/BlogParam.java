package cn.icexmoon.demo.repository.param;

import com.blinkfox.fenix.specification.annotation.Between;
import com.blinkfox.fenix.specification.annotation.Like;
import com.blinkfox.fenix.specification.handler.bean.BetweenValue;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BlogParam
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 下午3:26
 * @Version 1.0
 */
@Data
public class BlogParam {
    @Like
    private String title;
    @Like
    private String author;
    @Between("createTime")
    private BetweenValue<Date> createTime;
}
