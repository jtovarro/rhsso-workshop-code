package com.olleb;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

/**
 * @author Àngel Ollé Blázquez
 */
@ApplicationScoped
@RegisterRestClient(configKey = "com.olleb.extensions.api")
@ClientHeaderParam(name = "Authorization", value = "{token}")
public interface ExtensionsService {

    @GET
    Set<ExternalUser> getUsers();

    @GET
    @Path("{id}")
    ExternalUser getUserById(@PathParam("id") String userId);

    default String token() {
        return ConfigProvider.getConfig().getOptionalValue("extensions.token", String.class).orElseThrow();
    }

}
