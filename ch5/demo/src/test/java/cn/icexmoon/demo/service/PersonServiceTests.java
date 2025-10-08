package cn.icexmoon.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName PersonServiceTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午9:01
 * @Version 1.0
 */
@SpringBootTest
public class PersonServiceTests {
    @Autowired
    private PersonService personService;

    @Test
    public void testUpdateEmailById() {
        personService.UpdateEmailById("zsf@tom.com", 1L);
    }
}
