package com.harsh.homeworkModule3.Student_Management_System.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateRequest {
    @NotBlank(message = "Subject title must not be empty")
    @Size(min = 3,max = 50)
    private String title;

    @NotNull(message = "Professor ID must be provided")
    private Long professorId;

}
