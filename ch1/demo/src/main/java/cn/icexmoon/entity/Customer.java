package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Customer
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/3 下午7:40
 * @Version 1.0
 */
@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @TableGenerator(
            name = "customer_id_generator",
            table = "id_generator",
            pkColumnName = "table_name",
            valueColumnName = "value",
            pkColumnValue = "customer",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id_generator")
    private Long id;
    @Column(name = "last_name", length = 10, nullable = false)
    private String lastName;
    private Integer age;
    @Transient
    private Integer score;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private Date createTime;
}
