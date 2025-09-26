package org.example;


import org.example.dto.TelegramPost;
import org.example.dto.VkPost;
import org.example.service.PostFactory;
import org.example.service.TelegramApi;
import org.example.service.VKApi;


public class Main {

    public static void main(String[] args) throws Exception {
        TelegramApi bot = new TelegramApi();
        VKApi vkBot = new VKApi();

        TelegramPost tgPost = PostFactory.createTelegramPost("Привет!", "https://upload.wikimedia.org/wikipedia/commons/3/3e/Tree-256x256.png");
        VkPost vkPost = PostFactory.createVkPost("Привет!");


        bot.publish(
            tgPost
        );
        vkBot.publish(
            vkPost
        );

    }
}