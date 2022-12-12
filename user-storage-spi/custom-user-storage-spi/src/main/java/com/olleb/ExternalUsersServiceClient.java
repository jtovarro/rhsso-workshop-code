package com.olleb;

import java.util.Optional;
import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 * this class acts as a rest client of the REST_TARGET_URL external webservice.
 * it has been developed fo wildfly v15.
 * no cdi due lack of supportability at keycloak provider level (user storage
 * provider)
 * no use of RestClient EAP XP/Microprofile
 */
public class ExternalUsersServiceClient {

    // no injection
    private static final String TARGET_HOST = Optional
            .ofNullable(System.getenv("USER_STORAGE_CUSTOM_SPI_TARGET_HOST"))
            .orElse("localhost:8081");
    private static final String TARGET_ENDPOINT = "http://" + TARGET_HOST + "/api/v1/users";

    private ExternalUsersServiceClient() {
    }

    public static Set<ExternalUser> getUsers() {
        GenericType<Set<ExternalUser>> externalUsersListType = new GenericType<>() {
        };
        return ClientBuilder.newClient().target(TARGET_ENDPOINT).request().get(externalUsersListType);
    }

    public static ExternalUser getUserById(String id) {
        return ClientBuilder.newClient().target(TARGET_ENDPOINT).path("{id}").resolveTemplate("id", id).request()
                .get(ExternalUser.class);
    }

}
