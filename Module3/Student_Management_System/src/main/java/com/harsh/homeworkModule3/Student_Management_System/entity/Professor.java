package com.harsh.homeworkModule3.Student_Management_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "professor")

public class Professor extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    /* Inverse side of Student–Professor */
    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    private List<Student> students;

    /* One professor teaches many subjects */
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Subject> subjects;


}
