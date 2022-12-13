package com.olleb;

import java.lang.invoke.MethodHandles;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;

/**
 * @author Àngel Ollé Blázquez
 */
@ApplicationScoped
public class StartupListener {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    @RestClient
    ExtensionsService extensionsService;

    void onStart(@Observes StartupEvent ev) {
        Set<ExternalUser> users = extensionsService.getUsers();
        LOGGER.info(users);
        ExternalUser user = extensionsService.getUserById("1");
        LOGGER.info(user);
    }

}
