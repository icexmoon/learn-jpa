package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassRoom3
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 下午5:30
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "classroom3")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ClassRoom3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student3> students;

    public ClassRoom3(String name) {
        this.name = name;
    }

    public Student3 addStudent(@NonNull Student3 student) {
        if (student.alreadyInOtherClassRoom(this)) {
            throw new RuntimeException("Student already in other class room.");
        }
        if (students == null) {
            students = new ArrayList<>();
        }
        if (students.contains(student)) {
            return students.stream().filter(s -> s.equals(student)).findFirst().get();
        }
        students.add(student);
        return student;
    }

    public Student3 addStudent(@NonNull Student3 student, boolean dealAssociation) {
        Student3 addedStudent = this.addStudent(student);
        if (dealAssociation) {
            addedStudent.joinClassRoom(this);
        }
        return addedStudent;
    }
}
