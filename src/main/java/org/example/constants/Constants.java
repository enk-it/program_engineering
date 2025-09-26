package org.example.constants;

import io.github.cdimascio.dotenv.Dotenv;

public class Constants {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String TELEGRAM_API_KEY = dotenv.get("TELEGRAM_KEY");
    public static final long TELEGRAM_CHAT_ID = Long.parseLong(dotenv.get("TELEGRAM_CHAT_ID"));
    public static final String API_VERSION = "5.199";
    public static final String VK_API_KEY = dotenv.get("VK_KEY");
    public static final int GROUP_ID = 232902041;
}