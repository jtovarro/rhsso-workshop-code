package com.olleb.authenticator;

import java.lang.invoke.MethodHandles;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.common.util.SecretGenerator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.theme.Theme;

import com.olleb.service.TelegramService;

/**
 * @author Àngel Ollé Blázquez
 */
public class TelegramAuthenticator implements Authenticator {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    private static final String REQUIRED_ACTION = "telegram-otp-required-action";
    private static final String TEMPLATE = "telegram-otp.ftl";
    private static final String TELEGRAM_ID_ATTRIBUTE = "telegram_id";
    private static final String TELEGRAM_MSG_PROPERTY_DESC = "telegram-otp-desc";

    @Override
    public void close() {
        //
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        Map<String, String> config = context.getAuthenticatorConfig().getConfig();
        KeycloakSession keycloakSession = context.getSession();

        String id = context.getUser().getFirstAttribute(TELEGRAM_ID_ATTRIBUTE);

        try {
            int length = Integer.parseInt(config.get("length"));
            String code = SecretGenerator.getInstance().randomString(length, SecretGenerator.DIGITS);

            // EN only for this demo
            Theme theme = keycloakSession.theme().getTheme(Theme.Type.LOGIN);
            String msg = theme.getMessages(Locale.ENGLISH).getProperty(TELEGRAM_MSG_PROPERTY_DESC);

            msg = String.format(msg, code);

            TelegramService.getInstance().send(id, msg);

            Response response = context.form().createForm(TEMPLATE);
            context.challenge(response);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // TODO
        context.success();
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        String id = user.getFirstAttribute(TELEGRAM_ID_ATTRIBUTE);
        return !(id == null || id.isBlank());
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        user.addRequiredAction(REQUIRED_ACTION);
    }

}
