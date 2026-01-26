package com.Module5.Spring_Security_App.dto;

import com.Module5.Spring_Security_App.entities.enums.UserRole;
import com.nimbusds.oauth2.sdk.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDTO {
  private  String email;
   private String password;
   private String name;
   private Set<UserRole> roles;
}

