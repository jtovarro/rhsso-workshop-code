package com.olleb.authenticator;

public final class TelegramAuthenticatorConstants {

    static final String PROVIDER_ID = "telegram-authenticator";

    static final String REQUIRED_ACTION = "telegram-otp-required-action";
    static final String TEMPLATE = "telegram-otp.ftl";
    static final String TELEGRAM_ID_ATTRIBUTE = "telegram_id";
    static final String TELEGRAM_MSG_PROPERTY_DESC = "telegram-otp-desc";

    static final String DISPLAY_TYPE = "Telegram Authentication";
    static final String REFERENCE_CATEGORY = "otp";
    static final String HELP_TEXT = "OTP Telegram Authentication";

    // length config properties
    static final String LC_NAME = "length";
    static final String LC_LABEL = "Code length";
    static final String LC_HELP_TEXT = "Number of digits of the security code";
    static final int LC_DEFAULT_VALUE = 6;

    private TelegramAuthenticatorConstants() {
    }

}
