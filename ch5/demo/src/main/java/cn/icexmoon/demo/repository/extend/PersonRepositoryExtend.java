package cn.icexmoon.demo.repository.extend;

import cn.icexmoon.demo.entity.Person;
import org.springframework.data.domain.Page;

/**
 * @ClassName PersonRepositoryExtend
 * @Description 自定义的对 PersonRepository 的扩展
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 上午11:55
 * @Version 1.0
 */
public interface PersonRepositoryExtend {
    Page<Person> pageAndSearch(int pageNum, int pageSize, String lastName);
}
