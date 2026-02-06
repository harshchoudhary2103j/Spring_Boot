package com.example.Spring_ai.dto;

public record Joke(
        String text,
        String category,
        Double laughScore,
        Boolean isNSFW
) {
}
