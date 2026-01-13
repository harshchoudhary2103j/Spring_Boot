package com.harsh.homeworkModule3.Student_Management_System.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionCreateRequest {
    @NotNull(message = "Fees Should not be NUll")
    @Positive(message = "Fees Should be Greater than Zero")
    private Integer fees;
}
