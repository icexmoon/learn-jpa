package cn.icexmoon.entity.o2o;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName EmployeeInfo
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/29 上午8:41
 * @Version 1.0
 */
@Table(name = "tb_employee_info")
@Entity
@Data
@NoArgsConstructor
public class EmployeeInfo {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;
    private String address;
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date birthday;

    public EmployeeInfo(String address, String phone, Date birthday) {
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
    }
}
