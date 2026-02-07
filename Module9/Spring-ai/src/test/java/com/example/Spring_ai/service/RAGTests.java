package com.example.Spring_ai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RAGTests {

    @Autowired
    private RAGService ragService;

    @Test
    public void testIngest() {
        ragService.ingestVectorStore();
    }

    @Test
    public void testAskAI() {
        String res = ragService.askAI("How to connect to my Discord Account?");
        System.out.println(res);
    }
}
