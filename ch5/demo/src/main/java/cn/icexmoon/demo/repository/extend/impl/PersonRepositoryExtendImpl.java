package cn.icexmoon.demo.repository.extend.impl;

import cn.icexmoon.demo.entity.Person;
import cn.icexmoon.demo.repository.extend.PersonRepositoryExtend;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PersonRepositoryExtendImpl
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/8 上午11:57
 * @Version 1.0
 */
public class PersonRepositoryExtendImpl implements PersonRepositoryExtend {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Page<Person> pageAndSearch(int pageNum, int pageSize, String lastName) {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.lastName like :lastName", Person.class);
        query.setParameter("lastName", "%"+lastName+"%");
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        return new PageImpl<>(query.getResultList(), PageRequest.of(pageNum - 1, pageSize), searchTotal(lastName));
    }

    private long searchTotal(String lastName) {
        TypedQuery<Long> query = entityManager.createQuery("select count(p) from Person p where p.lastName like :lastName", Long.class);
        query.setParameter("lastName", "%"+lastName+"%");
        return query.getSingleResult();
    }
}
