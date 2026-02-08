package com.example.Spring_ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class TravellingTools {

    @Tool(description = "Get weather in a city")
    public String getWeather(@ToolParam(description = "City name for which to get weather info") String city){
        return switch (city){
            case "Delhi" -> "Sunny";
            case "Mumbai" -> "Cloudy";
            default -> "Rainy";
        };
    }
}
