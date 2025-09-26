package org.example.service;

import org.example.dto.VkPost;
import org.example.interfaces.IPost;
import org.example.interfaces.ISocial;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.example.constants.Constants.*;

public class VKApi implements ISocial {

    private void postToWall(String message) {
        HttpClient client = HttpClient.newHttpClient();

        String params = "owner_id=-" + GROUP_ID +
                "&from_group=1" +
                "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8) +
                "&access_token=" + VK_API_KEY +
                "&v=" + API_VERSION;

        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create("https://api.vk.com/method/wall.post"));
        builder.header("Content-Type", "application/x-www-form-urlencoded");
        builder.POST(HttpRequest.BodyPublishers.ofString(params));
        HttpRequest request = builder
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Ответ VK API: " + response.body());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void publish(IPost post) {
        if (!(post instanceof VkPost)) {
            throw new RuntimeException();
        }

        VkPost vkPost = (VkPost) post;

        if (vkPost.text != null) {
            postToWall(vkPost.text);
        }
    }
}
