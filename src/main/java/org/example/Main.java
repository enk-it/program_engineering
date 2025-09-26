package org.example;


import org.example.service.TelegramApi;
import org.example.service.VKApi;

import static org.example.constants.Constants.TELEGRAM_CHAT_ID;

public class Main {

    public static void main(String[] args) throws Exception {
        TelegramApi bot = new TelegramApi();
        VKApi vkBot = new VKApi();

        bot.sendMessage("ASDADA", TELEGRAM_CHAT_ID);
        vkBot.postToWall("ASDADA");
    }
}