package com.olleb.action;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * @author Àngel Ollé Blázquez
 */
public class TelegramRequiredActionFactory implements RequiredActionFactory {

    private static final TelegramRequiredAction TELEGRAM_REQUIRED_ACTION = new TelegramRequiredAction();

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return TELEGRAM_REQUIRED_ACTION;
    }

    @Override
    public String getId() {
        return TelegramRequiredActionConstants.PROVIDER_ID;
    }

    @Override
    public String getDisplayText() {
        return TelegramRequiredActionConstants.DISPLAY_TEXT;
    }

    @Override
    public void init(Scope config) {
        // no-op
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // no-op
    }

    @Override
    public void close() {
        // no-op
    }

}
