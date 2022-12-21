package com.olleb.service;

import java.util.Optional;

public final class TelegramServiceConstants {

    static final String TELEGRAM_BOT_USERNAME = Optional.ofNullable(System.getenv("TELEGRAM_BOT_USERNAME"))
            .orElseThrow(IllegalArgumentException::new);

    static final String TELEGRAM_BASE_URL = "https://api.telegram.org/bot{apitoken}";
    static final int SUBSCRIPTIONCODE_LENGTH = 10;

    // API params
    

    private TelegramServiceConstants() {
    }
}
