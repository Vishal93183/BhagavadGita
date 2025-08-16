package com.example.BhagavadGita;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*") // or limit to https://lovable.dev or loca.lt
public class ChatController {

    private final RAGService ragService;

    public ChatController(RAGService ragService) {
        this.ragService = ragService;
    }

  @GetMapping("/generate")
public Map<String, String> generate(@RequestParam(defaultValue = "Tell me about karma yoga") String message) {
    try {
        String answer = ragService.generateAnswer(message);
        return Map.of("generation", answer);
    } catch (Exception e) {
        e.printStackTrace(); // 👈 This will print the root cause in logs
        return Map.of("error", e.getMessage());
    }
}
}