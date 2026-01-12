package com.HospitalManagement.HospitalManagement.entity;

import com.HospitalManagement.HospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private BloodGroupType gender;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_insurance",unique = true)
    private Insurance insurance; //owning side

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL) // inverse side
    private Set<Appointment> appointments = new HashSet<>();
}
