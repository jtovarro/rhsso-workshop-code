package com.olleb.authenticator;

import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

/**
 * @author Àngel Ollé Blázquez
 */
public class TelegramAuthenticatorFactory implements AuthenticatorFactory {

    private static final TelegramAuthenticator TELEGRAM_AUTHENTICATOR = new TelegramAuthenticator();

    private static final List<ProviderConfigProperty> configProperties;

    static {
        configProperties = List.of(
                new ProviderConfigProperty(
                        TelegramAuthenticatorConstants.LC_NAME,
                        TelegramAuthenticatorConstants.LC_LABEL,
                        TelegramAuthenticatorConstants.LC_HELP_TEXT,
                        ProviderConfigProperty.STRING_TYPE,
                        TelegramAuthenticatorConstants.LC_DEFAULT_VALUE));
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return TELEGRAM_AUTHENTICATOR;
    }

    @Override
    public String getId() {
        return TelegramAuthenticatorConstants.PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return TelegramAuthenticatorConstants.DISPLAY_TYPE;
    }

    @Override
    public String getReferenceCategory() {
        return TelegramAuthenticatorConstants.REFERENCE_CATEGORY;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getHelpText() {
        return TelegramAuthenticatorConstants.HELP_TEXT;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
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
