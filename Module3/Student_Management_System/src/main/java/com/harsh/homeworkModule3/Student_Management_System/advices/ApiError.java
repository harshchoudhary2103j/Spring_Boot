package com.harsh.homeworkModule3.Student_Management_System.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String message;
    private List<String> subErrors;

}
