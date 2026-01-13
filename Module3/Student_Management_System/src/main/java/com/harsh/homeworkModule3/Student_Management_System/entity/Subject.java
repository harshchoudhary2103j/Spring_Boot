package com.harsh.homeworkModule3.Student_Management_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    /* Owning side: subject belongs to one professor */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    /* Inverse side of Student–Subject */
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
