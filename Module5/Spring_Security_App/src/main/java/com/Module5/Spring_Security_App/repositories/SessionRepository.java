package com.Module5.Spring_Security_App.repositories;

import com.Module5.Spring_Security_App.entities.Session;
import com.Module5.Spring_Security_App.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findByUser(User user);

     Optional<Session>findByRefreshToken(String refreshToken);
}
