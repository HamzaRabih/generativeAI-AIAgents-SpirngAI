package ma.enset.iibdcc.bdccai2.controllers;

import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageGenerationContreller {
    private OpenAiImageModel openAiImageModel;

    public ImageGenerationContreller(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/generateImage")
    public String generateImage(String promt) {
        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .quality("hd")
                .model("dall-e-3")
                .build();
        ImagePrompt imagePrompt=new ImagePrompt(promt,imageOptions);
        String url=openAiImageModel.call(imagePrompt).getResult().getOutput().getUrl();
        return url;

    }
}
