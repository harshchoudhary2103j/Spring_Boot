package com.Module5.Spring_Security_App.services;

import com.Module5.Spring_Security_App.dto.LoginDTO;
import com.Module5.Spring_Security_App.dto.SignupDTO;
import com.Module5.Spring_Security_App.dto.UserDTO;
import com.Module5.Spring_Security_App.entities.User;
import com.Module5.Spring_Security_App.exceptions.ResourceNotFoundException;
import com.Module5.Spring_Security_App.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Username not found"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User wit Id not found"+userId));
    }

    public UserDTO signup(SignupDTO signupDTO) {
        Optional<User>user = userRepository.findByEmail(signupDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists: "+signupDTO.getEmail());
        }
        User toCreate = modelMapper.map(signupDTO,User.class);
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
        User savedUser = userRepository.save(toCreate);
        return modelMapper.map(savedUser, UserDTO.class);
    }


}
