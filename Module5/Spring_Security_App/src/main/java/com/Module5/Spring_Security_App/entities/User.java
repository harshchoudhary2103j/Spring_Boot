package com.Module5.Spring_Security_App.entities;

import com.Module5.Spring_Security_App.entities.enums.Permission;
import com.Module5.Spring_Security_App.entities.enums.UserRole;
import com.Module5.Spring_Security_App.util.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority>authorities= new HashSet<>();
        roles.forEach(
                userRole -> {
                   Set<SimpleGrantedAuthority>permissions = PermissionMapping.getAuthoritiesforRole(userRole);
                   authorities.addAll(permissions);
                   authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.name()));
                }
        );
        return  authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
