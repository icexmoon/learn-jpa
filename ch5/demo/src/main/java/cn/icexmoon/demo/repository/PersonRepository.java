package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Person;
import cn.icexmoon.demo.repository.extend.PersonRepositoryExtend;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName PersonRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/7 下午6:42
 * @Version 1.0
 */

public interface PersonRepository
        extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person>, PersonRepositoryExtend {
    Person findByLastName(String lastName);

    // where lastName like ?'%' and birth<= ?
    Person findByLastNameStartsWithAndBirthBefore(String lastName, LocalDate birth);

    @Query("select p from Person p where p.id=(select max(p2.id) from Person p2)")
    Person getMaxIdPerson();

    @Query("select p from Person p where p.lastName=?1 and p.email=?2")
    List<Person> queryTest(String lastName, String email);

    @Query("select p from Person p where p.lastName=:lastName and p.email=:email")
    List<Person> queryTest2(@Param("email") String email, @Param("lastName") String lastName);

    @Query("select p from Person p where p.lastName like %:lastName% and p.email like %:email%")
    List<Person> queryTest3(@Param("email") String email, @Param("lastName") String lastName);

    @Query(value = "select count(*) from tb_person", nativeQuery = true)
    int getTotalCount();

    @Modifying
    @Query("update Person p set p.email=:email where p.id=:id")
    void updateEmailById(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Query("delete from Person p where p.id=:id")
    void deleteById(@NonNull @Param("id") Long id);
}
