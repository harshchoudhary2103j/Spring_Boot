package com.harsh.homeworkModule3.Student_Management_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admission_record")
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer fees;

    /* Owning side: FK stored here */
    @OneToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            unique = true
    )
    private Student student;
}
