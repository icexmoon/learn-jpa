package cn.icexmoon.entity.m2m;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Class
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午8:13
 * @Version 1.0
 */
@Table(name = "tb_course")
@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JoinTable(name = "tb_course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @ManyToMany
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }
}
