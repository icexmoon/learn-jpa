package cn.icexmoon.entity;

import cn.icexmoon.converter.EnumSetConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.EnumSet;

/**
 * @ClassName Week
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午4:34
 * @Version 1.0
 */
@Entity
@Data
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = EnumSetConverter.class)
    private EnumSet<DayOfWeek> workDays;
    @Convert(converter = EnumSetConverter.class)
    private EnumSet<DayOfWeek> holidays;
}
