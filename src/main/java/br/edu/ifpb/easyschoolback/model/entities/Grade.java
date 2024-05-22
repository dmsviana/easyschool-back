package br.edu.ifpb.easyschoolback.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_grades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Grade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "value", nullable = false)
    @Positive
    private Double value;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GradeType type;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


}
