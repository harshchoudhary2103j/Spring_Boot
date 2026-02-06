package com.example.Spring_ai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AITests {
    @Autowired
    private AIService aiService;
    @Test
    public void testGetJoke(){
        var joke = aiService.getJoke("Dogs");
        System.out.println(joke);
    }
    @Test
    public void testEmbed(){
        var embed = aiService.getEmbedding("Dogs");
        System.out.println(embed.length);
        for(float f:embed){
            System.out.print(f+" ");
        }
    }
    @Test
    public void testStoreData(){
        aiService.ingestDataToVectorStore();
    }

    @Test
    public void testSearch(){
        var res = aiService.similaritySearch("movies of different genre");
        for(var doc:res){
            System.out.println(doc.getMetadata().get("title"));
        }
    }

}
