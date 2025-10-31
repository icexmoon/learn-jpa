package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

/**
 * @ClassName Week
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午4:34
 * @Version 1.0
 */
@Entity
@Data
public class Week3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<DayOfWeek> workDays;
    @ElementCollection
    private List<DayOfWeek> holidays;
}
