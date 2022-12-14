package com.olleb.action;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * @author Àngel Ollé Blázquez
 */
public class TelegramOTPRequiredActionFactory implements RequiredActionFactory {

    private static final TelegramOTPRequiredAction TELEGRAM_REQUIRED_ACTION = new TelegramOTPRequiredAction();

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return TELEGRAM_REQUIRED_ACTION;
    }

    @Override
    public void init(Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return TelegramOTPRequiredAction.PROVIDER_ID;
    }

    @Override
    public String getDisplayText() {
        return "Telegram ID";
    }

}
