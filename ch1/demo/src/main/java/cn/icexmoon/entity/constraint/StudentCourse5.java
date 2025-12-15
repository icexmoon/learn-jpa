package cn.icexmoon.entity.constraint;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName StudentCourse5
 * @Description 学生选课实体类
 * @Author icexmoon@qq.com
 * @Date 2025/12/15 上午8:56
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "student_course5", uniqueConstraints =
    @UniqueConstraint(name = "unique_student_course5", columnNames = {"student_id", "course_id"}))
public class StudentCourse5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "course_id")
    private Long courseId;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
