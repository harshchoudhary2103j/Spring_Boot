package com.example.TestingApplications;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@Slf4j
class TestingApplicationsApplicationTests {

    @BeforeEach
    void setUp(){
        log.info("Starting the method, setting up config");
    }
    @AfterEach
    void tearDown(){
        log.info("Tearing down the method");
    }
    @BeforeAll
    static void setUpOnce(){
        log.info("Setting Up Once....");
    }
    @AfterAll
    static void tearDownOnce(){
        log.info("Tearing down all....");
    }

    @Test
    void testNumberOne(){
        int a = 5;
        int b = 3;
        int res = a+b;
        Assertions.assertThat(res).isEqualTo(8).isCloseTo(9, Offset.offset(1));
    }
    @Test
    void testDivideTwoNumbers_whenDenominatorIsZero_ThenArithmeticException(){
        int a = 5;
        int b = 0;
        Assertions.assertThatThrownBy(()->divideTwoNumbers(a,b))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Tried to divide by zero");
    }



	@Test
	void contextLoads() {
	}

    int addTwoNumbers(int a, int b){
        return a+b;
    }
    double divideTwoNumbers(int a ,int b){
        try{
            return a/b;
        }catch (ArithmeticException e){
            log.error("Arithmetic Exception occurred: {}", e.getLocalizedMessage());
            throw new ArithmeticException("Tried to divide by zero");
        }
    }

}
