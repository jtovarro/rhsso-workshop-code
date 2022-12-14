package com.olleb.action;

import java.lang.invoke.MethodHandles;
import java.util.Locale;
import java.util.function.Predicate;

import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.common.util.SecretGenerator;
import org.keycloak.theme.Theme;

import com.olleb.service.TelegramMessage;
import com.olleb.service.TelegramService;

/**
 * @author Àngel Ollé Blázquez
 */
public class TelegramOTPRequiredAction implements RequiredActionProvider {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    private static final String TEMPLATE = "telegram-id-config.ftl";
    private static final String TELEGRAM_ID_ATTRIBUTE = "telegram_id";
    private static final String TELEGRAM_USERNAME_ATTRIBUTE = "telegram_username";
    private static final String TELEGRAM_ROLLING_CODE_ATTRIBUTE = "telegram_code";
    private static final String TELEGRAM_SECURITY_CODE_ATTRIBUTE = "scode";
    private static final int TELEGRAM_ROLLING_CODE_LENGTH = 6;

    private static final TelegramService TELEGRAM_SERVICE = TelegramService.getInstance();

    private static final Predicate<String> BLANK = s -> s == null || s.isBlank();

    protected static final String PROVIDER_ID = "telegram-otp-required-action";

    @Override
    public void close() {
    }

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
        String telegramId = context.getUser().getFirstAttribute(TELEGRAM_ID_ATTRIBUTE);
        if (BLANK.test(telegramId)) {
            context.getUser().addRequiredAction(PROVIDER_ID);
        }
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        try {
            Theme theme = context.getSession().theme().getTheme(Theme.Type.LOGIN);
            String msg = theme.getMessages(Locale.ENGLISH).getProperty("telegram-id-label");
            String code = SecretGenerator.getInstance().randomString(TELEGRAM_ROLLING_CODE_LENGTH,
                    SecretGenerator.DIGITS);

            context.getAuthenticationSession().setAuthNote(TELEGRAM_SECURITY_CODE_ATTRIBUTE, code);

            msg = String.format(msg, code, TelegramService.TELEGRAM_BOT_USERNAME);

            Response response = context.form()
                    .setAttribute("msgcode", msg)
                    .createForm(TEMPLATE);

            context.challenge(response);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void processAction(RequiredActionContext context) {
        String subscriptionCode = context.getHttpRequest().getDecodedFormParameters()
                .getFirst(TELEGRAM_ROLLING_CODE_ATTRIBUTE);

        if (BLANK.test(subscriptionCode)) {
            requiredActionChallenge(context);
            return;
        }

        String code = context.getAuthenticationSession().getAuthNote(TELEGRAM_SECURITY_CODE_ATTRIBUTE);
        TelegramMessage message = TELEGRAM_SERVICE.getMessageFromSecurityCode(code);

        if (message != null && message.getSubscriptionCode().equals(subscriptionCode)) {
            String userId = message.getId();
            String username = message.getUsername();

            context.getUser().setSingleAttribute(TELEGRAM_ID_ATTRIBUTE, userId);
            context.getUser().setSingleAttribute(TELEGRAM_USERNAME_ATTRIBUTE, username);
            context.getUser().removeRequiredAction(PROVIDER_ID);

            context.success();
            return;
        }

        requiredActionChallenge(context);
    }

}
