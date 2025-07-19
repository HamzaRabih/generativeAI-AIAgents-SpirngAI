package ma.enset.iibdcc.bdccai2.controllers;

import ma.enset.iibdcc.bdccai2.controllers.outputs.CinMode;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MultiModalController {

    ChatClient chatClient;
    @Value("classpath:/images/CIN_maroc.jpg")
    private Resource image;

    public MultiModalController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


   /*
    @GetMapping("/describe")
    public CinMode describeImage() {
        return chatClient.prompt()
                .system("Donne moi une description de l image fournie")
                .user(u->u.text("Décrire cette image")
                        .media(MediaType.IMAGE_JPEG ,image)
                )
                .call()
                .entity(CinMode.class);
    }
    */

    @PostMapping(value = "/askImage",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public String askImage(@RequestParam(name = "file") MultipartFile file,String question) throws IOException {
        byte[] bytes=file.getBytes();
        return chatClient.prompt()
                .system("Répond à la question de l'utilisateur à propos de l'image fournite")
                .user(u->
                        u.text(question)
                        .media(MediaType.IMAGE_JPEG ,new ByteArrayResource(bytes))
                )
                .call()
                .content();
    }
}
