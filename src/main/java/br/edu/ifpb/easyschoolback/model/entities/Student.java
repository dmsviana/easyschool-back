package br.edu.ifpb.easyschoolback.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_students")
@Entity
public class Student extends User {

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_phone")
    private String parentPhone;


    @PrePersist
    private void validate() {
        int age = getAge();
        if (age < 18 && (this.getParentName() == null || this.getParentPhone() == null)) {
            throw new IllegalArgumentException("Para alunos menores de 18 anos, é necessário informar o nome e telefone do responsável.");
        }
    }

    public Integer getAge() {
        return getDateOfBirth().until(LocalDate.now()).getYears();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();

        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();

        if (thisEffectiveClass != oEffectiveClass) return false;

        Student student = (Student) o;
        return getId() != null && Objects.equals(getId(), student.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }
}
