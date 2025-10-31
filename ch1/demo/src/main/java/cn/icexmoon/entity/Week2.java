package cn.icexmoon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Array;

import java.time.DayOfWeek;

/**
 * @ClassName Week
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午4:34
 * @Version 1.0
 */
@Entity
@Data
public class Week2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Array(length = 7)
    private DayOfWeek[] workDays;
    @Array(length = 2)
    private DayOfWeek[] holidays;
}
