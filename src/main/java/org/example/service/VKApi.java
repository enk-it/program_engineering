package org.example.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.example.constants.Constants.*;

public class VKApi {

    public void postToWall(String message) throws Exception {
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

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Ответ VK API: " + response.body());
    }

}
