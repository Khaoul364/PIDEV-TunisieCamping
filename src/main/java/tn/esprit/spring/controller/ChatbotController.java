package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.ChatbotRequest;
import tn.esprit.spring.entity.ChatbotResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatbotController {

    @PostMapping("/chatbot")
    public ChatbotResponse processMessage(@RequestBody ChatbotRequest request) {
        String userMessage = request.getMessage();

        // Process user message and generate bot reply
        String botReply = generateBotReply(userMessage);

        ChatbotResponse response = new ChatbotResponse();
        response.setReply(botReply);

        return response;
    }

    private String generateBotReply(String userMessage) {
        String botReply;

        // Check if the user's message contains a specific word
        if (userMessage.contains("hello")) {
            botReply = "Hello! How can I assist you?";
        } else if (userMessage.contains("I want to know are these activities available")) {
            botReply = "Sure, these are all the activity of the season 2023";
        } else if (userMessage.contains("bye")) {
            botReply = "Goodbye! Have a nice day!";
        } else {
            botReply = "I'm sorry, I didn't understand your message.";
        }

        return botReply;
    }

}
