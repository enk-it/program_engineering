package org.example.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import static org.example.constants.Constants.TELEGRAM_API_KEY;

public class TelegramApi {
    private final TelegramBot bot = new TelegramBot(TELEGRAM_API_KEY);

    public void sendMessage(String message, long chatId) {
        SendResponse response = bot.execute(new SendMessage(chatId, message));
    }

    public void listen() {
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
}
