package com.olleb.action;

public final class TelegramRequiredActionConstants {

    static final String PROVIDER_ID = "telegram-otp-required-action";
    static final String DISPLAY_TEXT = "Telegram ID";

    static final String TEMPLATE = "telegram-id-config.ftl";
    static final String TELEGRAM_ID_ATTRIBUTE = "telegram_id";
    static final String TELEGRAM_ID_PROPERTY = "telegram-id-label";
    static final String TELEGRAM_USERNAME_ATTRIBUTE = "telegram_username";
    static final String TELEGRAM_ROLLING_CODE_ATTRIBUTE = "telegram_code";
    static final String TELEGRAM_SECURITY_CODE_ATTRIBUTE = "scode";
    static final String TELEGRAM_MESSAGE_SECURITY_CODE_ATTRIBUTE = "msgcode";
    static final int TELEGRAM_ROLLING_CODE_LENGTH = 6;

    private TelegramRequiredActionConstants() {
        // no-op constructor
    }
}
