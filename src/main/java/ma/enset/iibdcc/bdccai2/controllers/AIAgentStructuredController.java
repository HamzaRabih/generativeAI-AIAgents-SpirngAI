package ma.enset.iibdcc.bdccai2.controllers;

import ma.enset.iibdcc.bdccai2.controllers.outputs.Movie;
import ma.enset.iibdcc.bdccai2.controllers.outputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIAgentStructuredController {
    private ChatClient chatClient;

    public AIAgentStructuredController(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                //.defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/askAgent")
    public MovieList askLLM(String query) {
        String systemMessage = """
                Vous éte un spécialiste dans un domaine de cinéma 
                Répond à la question de l'utilisateur à ce propos
                """;
        return chatClient.prompt()
                .user(query)
                .system(systemMessage)
                .call()
                .entity(MovieList.class);

    }


}
