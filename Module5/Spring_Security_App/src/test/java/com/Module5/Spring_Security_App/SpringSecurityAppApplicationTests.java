package com.Module5.Spring_Security_App;

import com.Module5.Spring_Security_App.entities.User;
import com.Module5.Spring_Security_App.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityAppApplicationTests {
    @Autowired
    private JwtService jwtService;


	@Test
	void contextLoads() {
//        User user = new User(4L,"harsh@gmail.com","harsh1234");
//        String token = jwtService.generateToken(user);
//        System.out.println(token);
//        Long id = jwtService.getUserIdFromToken(token);
//        System.out.println(id);
	}

}
