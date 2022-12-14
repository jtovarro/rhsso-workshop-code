package com.olleb.authenticator;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.jboss.logging.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    public static final String PROVIDER_ID = "telegram-authenticator";

    private static final List<ProviderConfigProperty> configProperties;

    static {
        configProperties = List.of(
                new ProviderConfigProperty("length", "Code length", "Number of digits of the security code",
                        ProviderConfigProperty.STRING_TYPE, 6));
    }

    private static final TelegramAuthenticator TELEGRAM_AUTHENTICATOR = new TelegramAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        return TELEGRAM_AUTHENTICATOR;
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
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Telegram Authentication";
    }

    @Override
    public String getReferenceCategory() {
        return "otp";
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
        return "OTP Telegram authentication";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

}
