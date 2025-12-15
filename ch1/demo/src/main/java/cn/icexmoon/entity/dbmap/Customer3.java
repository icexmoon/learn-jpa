package cn.icexmoon.entity.dbmap;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @ClassName Customer2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/10 下午4:06
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "customer3")
@SecondaryTables({
        @SecondaryTable(name = "customer_address3", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")),
        @SecondaryTable(name = "customer_base_info3", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
})
public class Customer3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(table = "customer_address3")
    private String city;
    @Column(table = "customer_address3")
    private String Address;
    @Column(table = "customer_base_info3")
    private String phone;
    @Column(table = "customer_base_info3")
    private Integer age;
}
