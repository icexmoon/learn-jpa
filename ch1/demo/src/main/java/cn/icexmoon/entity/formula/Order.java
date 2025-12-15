package cn.icexmoon.entity.formula;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;

/**
 * @ClassName Order
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/12 下午4:06
 * @Version 1.0
 */
@Entity
@Table(name = "`order`")
@Data
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(0)
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total; // 税前订单总价
    @NotNull
    @Min(0)
    @Column(name = "tax_rate", precision = 4, scale = 4)
    private BigDecimal taxRate; // 税率
    @Formula("total * tax_rate")
    private BigDecimal tax; // 税
    @Formula("total * (1 + tax_rate)")
    private BigDecimal totalWithTax; // 税后订单总价
}
