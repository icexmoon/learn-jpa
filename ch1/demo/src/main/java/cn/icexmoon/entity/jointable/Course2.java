package cn.icexmoon.entity.jointable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Course2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 下午4:55
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "course2")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;
    @ManyToMany(mappedBy = "couses")
    private List<Student2> students;

    public Course2(String name) {
        this.name = name;
    }

    public boolean choosedBy(Student2 student) {
        if (students == null) {
            return false;
        }
        return students.contains(student);
    }

    public Student2 addStudent(Student2 student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        if (students.contains(student)) {
            return students.stream().filter(s -> s.equals(student)).findFirst().get();
        }
        students.add(student);
        return student;
    }

    public Student2 addStudent(Student2 student, boolean dealAssociation){
        Student2 addedStudent = this.addStudent(student);
        if (dealAssociation){
            addedStudent.addCourse(this);
        }
        return addedStudent;
    }
}
