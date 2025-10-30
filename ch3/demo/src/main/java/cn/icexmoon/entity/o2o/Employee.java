package cn.icexmoon.entity.o2o;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

/**
 * @ClassName Employee
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/29 上午8:38
 * @Version 1.0
 */
@Table(name = "tb_employee")
@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String name;
    @NaturalId
    private String email;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmployeeInfo employeeInfo;

    public Employee(String email) {
        this.email = email;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
