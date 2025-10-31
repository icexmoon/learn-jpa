package cn.icexmoon.converter;

import jakarta.persistence.AttributeConverter;

import java.time.DayOfWeek;
import java.util.EnumSet;

/**
 * @ClassName EnumSetConverter
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午4:31
 * @Version 1.0
 */
public class EnumSetConverter
        // converts Java values of type EnumSet<DayOfWeek> to integers for storage in an INT column
        implements AttributeConverter<EnumSet<DayOfWeek>,Integer> {
    @Override
    public Integer convertToDatabaseColumn(EnumSet<DayOfWeek> enumSet) {
        int encoded = 0;
        var values = DayOfWeek.values();
        for (int i = 0; i<values.length; i++) {
            if (enumSet.contains(values[i])) {
                encoded |= 1<<i;
            }
        }
        return encoded;
    }

    @Override
    public EnumSet<DayOfWeek> convertToEntityAttribute(Integer encoded) {
        var set = EnumSet.noneOf(DayOfWeek.class);
        var values = DayOfWeek.values();
        for (int i = 0; i<values.length; i++) {
            if (((1<<i) & encoded) != 0) {
                set.add(values[i]);
            }
        }
        return set;
    }
}
