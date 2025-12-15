package cn.icexmoon.entity.joincolumn;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @ClassName Student3
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/11/24 下午5:30
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "student3")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Student3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassRoom3 classRoom;

    public Student3(String name) {
        this.name = name;
    }

    public boolean inClassRoom(@NonNull ClassRoom3 classRoom) {
        return classRoom.equals(this.classRoom);
    }

    public boolean alreadyInOtherClassRoom(@NonNull ClassRoom3 classRoom) {
        if (this.classRoom == null){
            return false;
        }
        return !this.inClassRoom(classRoom);
    }

    public ClassRoom3 joinClassRoom(@NonNull ClassRoom3 classRoom){
        if (inClassRoom(classRoom)){
            return this.classRoom;
        }
        this.classRoom = classRoom;
        return classRoom;
    }

    public ClassRoom3 joinClassRoom(@NonNull ClassRoom3 classRoom, boolean dealAssociation){
        ClassRoom3 joinedClassRoom = this.joinClassRoom(classRoom);
        if (dealAssociation){
            joinedClassRoom.addStudent(this);
        }
        return joinedClassRoom;
    }
}
