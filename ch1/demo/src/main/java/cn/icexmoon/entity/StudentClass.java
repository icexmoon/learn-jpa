package cn.icexmoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName StudentClass
 * @Description 学生选课表
 * @Author icexmoon@qq.com
 * @Date 2025/10/25 下午3:38
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class StudentClass {
    public record StudentClassId(Long classId, Long studentId) {
    }
    @EmbeddedId
    private StudentClassId id;

    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    public StudentClass(StudentClassId id, Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}
