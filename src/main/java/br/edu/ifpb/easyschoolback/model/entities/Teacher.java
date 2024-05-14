package br.edu.ifpb.easyschoolback.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_teachers")
@Entity
public class Teacher extends User {

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToMany
    @JoinTable(
            name = "tb_teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private List<Course> courses = new ArrayList<>();

    public void addCourse(final Course course) {
        this.courses.add(course);
        course.getTeachers().add(this);
    }

    public void removeCourse(final Course course) {
        this.courses.remove(course);
        course.getTeachers().remove(this);
    }
}
