package com.Module5.Spring_Security_App.controllers;

import com.Module5.Spring_Security_App.dto.LoginDTO;
import com.Module5.Spring_Security_App.dto.LoginResponseDTO;
import com.Module5.Spring_Security_App.dto.SignupDTO;
import com.Module5.Spring_Security_App.dto.UserDTO;
import com.Module5.Spring_Security_App.services.AuthService;
import com.Module5.Spring_Security_App.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO>signUp(@RequestBody SignupDTO signupDTO){
        UserDTO userDto = userService.signup(signupDTO);
        return  ResponseEntity.ok(userDto);

    }
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginDTO loginDTO,
            HttpServletResponse response
    ) {
        LoginResponseDTO token = authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); --> uses https for secure cookies
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO>refresh(HttpServletRequest request){
       String refreshToken =  Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found in the cookies"));

       LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);
       return ResponseEntity.ok(loginResponseDTO);

    }

}
