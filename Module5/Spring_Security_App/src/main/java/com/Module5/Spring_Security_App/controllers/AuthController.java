package com.Module5.Spring_Security_App.controllers;

import com.Module5.Spring_Security_App.dto.LoginDTO;
import com.Module5.Spring_Security_App.dto.SignupDTO;
import com.Module5.Spring_Security_App.dto.UserDTO;
import com.Module5.Spring_Security_App.services.AuthService;
import com.Module5.Spring_Security_App.services.UserService;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.CookiePolicy;

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
    public ResponseEntity<String>login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);

        return ResponseEntity.ok(token);
    }
}
