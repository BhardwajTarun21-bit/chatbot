package com.example.chatbot;

import com.example.chatbot.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatbotService.getChatbotResponse(message);
    }
}