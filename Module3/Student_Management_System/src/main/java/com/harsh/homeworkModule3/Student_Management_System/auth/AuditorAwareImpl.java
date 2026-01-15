package com.harsh.homeworkModule3.Student_Management_System.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get scurity context
        //get authentication
        //get the principle
        //get the username

        return Optional.of("Harsh Choudhary");
    }
}
