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
@Table(name = "customer2")
@SecondaryTable(name = "customer_address2", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Customer2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(table = "customer_address2")
    private String city;
    @Column(table = "customer_address2")
    private String Address;
}
