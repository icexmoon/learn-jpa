package cn.icexmoon.entity.m2m;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Student
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/5 下午8:13
 * @Version 1.0
 */
@Table(name = "tb_student")
@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }
}
