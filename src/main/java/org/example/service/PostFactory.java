package org.example.service;

import org.example.dto.TelegramPost;
import org.example.dto.VkPost;

public class PostFactory {

    public static TelegramPost createTelegramPost (
            String text
    ) {
        TelegramPost post = new TelegramPost();
        post.text = text;
        return post;
    }

    public static TelegramPost createTelegramPost (
            String text,
            String stickerUrl
    ) {
        TelegramPost post = new TelegramPost();
        post.text = text;
        post.mediaUrl = stickerUrl;
        return post;
    }

    public static VkPost createVkPost (
            String text
    ) {
        VkPost post = new VkPost();
        post.text = text;
        return post;
    }
}
