package com.Module5.Spring_Security_App.services;

import com.Module5.Spring_Security_App.dto.LoginDTO;
import com.Module5.Spring_Security_App.dto.LoginResponseDTO;
import com.Module5.Spring_Security_App.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user =(User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        //genereate session
        sessionService.generateNewSession(user,refreshToken);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);


    }

    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);

        //check if refresh token is valid i.e. present in db
        sessionService.validateSession(refreshToken);

        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDTO(userId,accessToken,refreshToken);
    }
}
