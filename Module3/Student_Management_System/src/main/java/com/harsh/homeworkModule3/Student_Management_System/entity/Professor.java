package com.harsh.homeworkModule3.Student_Management_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "professor")
public class Professor {

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
