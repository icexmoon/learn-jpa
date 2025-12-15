package cn.icexmoon.entity.jointable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Student2
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 下午4:55
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "student2")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course2", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course2> couses;

    public Student2(String name) {
        this.name = name;
    }

    public Course2 addCourse(Course2 course){
        if(couses == null){
            couses = new ArrayList<>();
        }
        if(couses.contains(course)){
            return couses.stream().filter(c -> c.equals(course)).findFirst().get();
        }
        couses.add(course);
        return course;
    }

    public Course2 addCourse(Course2 course, boolean dealAssociation){
        Course2 addedCourse = this.addCourse(course);
        if(dealAssociation){
            addedCourse.addStudent(this);
        }
        return addedCourse;
    }
}
