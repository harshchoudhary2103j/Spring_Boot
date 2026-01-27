package com.Module5.Spring_Security_App.util;

import com.Module5.Spring_Security_App.entities.Session;
import com.Module5.Spring_Security_App.entities.User;
import com.Module5.Spring_Security_App.entities.enums.*;
import com.Module5.Spring_Security_App.entities.enums.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Module5.Spring_Security_App.entities.enums.Permission.*;
import static com.Module5.Spring_Security_App.entities.enums.UserRole.*;

public class PermissionMapping {
   private static final Map<UserRole, Set<Permission>>map = Map.of(
           USER,Set.of(USER_VIEW,POST_VIEW),
            CREATOR,Set.of(USER_VIEW,POST_VIEW,USER_UPDATE,POST_UPDATE),
            ADMIN, Set.of(USER_DELETE, USER_CREATE,USER_UPDATE,POST_CREATE,POST_UPDATE,POST_DELETE)
    );
   public static Set<SimpleGrantedAuthority>getAuthoritiesforRole(UserRole role){
       return map.get(role).stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
   }
}
