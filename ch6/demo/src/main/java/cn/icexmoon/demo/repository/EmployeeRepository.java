package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Employee;
import com.blinkfox.fenix.jpa.FenixJpaRepository;
import com.blinkfox.fenix.jpa.QueryFenix;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName EmployeeRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午10:04
 * @Version 1.0
 */
public interface EmployeeRepository extends FenixJpaRepository<Employee, Long> {
    @QueryFenix
    List<Employee> queryEmployees(@Param("employee") Employee employee);
}
