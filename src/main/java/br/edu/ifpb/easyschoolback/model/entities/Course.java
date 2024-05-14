package br.edu.ifpb.easyschoolback.model.entities;

import br.edu.ifpb.easyschoolback.model.repository.exception.MaximumCapacityReachedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_courses")
@EntityListeners(AuditingEntityListener.class)
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "maximum_capacity", nullable = false)
    private Integer maxCapacity;

    @Column(name = "min_age", nullable = false)
    private Integer minAge;

    @Column(name = "max_age", nullable = false)
    private Integer maxAge;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "tb_course_days", joinColumns = @JoinColumn(name = "course_id"))
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> courseDays;

    @OneToMany(fetch = EAGER)
    @JsonIgnore
    private List<Student> students;

    @ManyToMany(mappedBy = "courses")
    private List<Teacher> teachers = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public void addStudent(final Student student) {
        checkCourseCapacity();
        checkStudentAge(student);
        this.students.add(student);
    }

    public void removeStudent(final Long studentId) {
        this.students.removeIf(student -> student.getId().equals(studentId));
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getCourses().remove(this);
    }

    @PrePersist
    public void prePersist() {
        if (students == null) {
            students = List.of();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    private void checkCourseCapacity() {
        if (this.maxCapacity <= this.students.size()) {
            throw new MaximumCapacityReachedException("A capacidade máxima do curso foi atingida.");
        }
    }

    private void checkStudentAge(final Student student) {
        if (student.getAge() < this.minAge || student.getAge() > this.maxAge) {
            throw new IllegalArgumentException("A idade do aluno não está dentro da faixa etária do curso.");
        }
    }
}