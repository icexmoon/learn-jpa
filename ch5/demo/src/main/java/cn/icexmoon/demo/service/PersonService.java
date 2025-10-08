package cn.icexmoon.demo.service;

import cn.icexmoon.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PersonService
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午8:59
 * @Version 1.0
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Transactional
    public void UpdateEmailById(String email, Long id) {
        personRepository.updateEmailById(email, id);
    }
}
