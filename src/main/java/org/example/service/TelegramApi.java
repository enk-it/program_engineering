package org.example.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import com.pengrad.telegrambot.response.SendResponse;
import org.example.dto.TelegramPost;
import org.example.interfaces.IPost;
import org.example.interfaces.ISocial;

import static org.example.constants.Constants.TELEGRAM_API_KEY;
import static org.example.constants.Constants.TELEGRAM_CHAT_ID;

public class TelegramApi implements ISocial {
    private final TelegramBot bot = new TelegramBot(TELEGRAM_API_KEY);

    private void sendMessage(String message) {
        SendResponse response = bot.execute(new SendMessage(TELEGRAM_CHAT_ID, message));
    }

    private void sendSticker(String stickerUrl) {
        SendResponse response = bot.execute(new SendSticker(TELEGRAM_CHAT_ID, stickerUrl));
        System.out.println(response);
    }

    private void listen() {
        bot.setUpdatesListener(updates -> {
            System.out.println(updates);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                System.out.println(e);
                e.response().errorCode();
                e.response().description();
            } else {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void publish(IPost post) {
        if (!(post instanceof TelegramPost)) {
            throw new RuntimeException();
        }

        TelegramPost tgPost = (TelegramPost) post;

        if (tgPost.text != null) {
            sendMessage(tgPost.text);
        }

        if (tgPost.mediaUrl != null) {
            sendSticker(tgPost.mediaUrl);
        }
    }
}
