package com.olleb;

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

    private static final String REST_TARGET_URL = "http://localhost:8081/api/v1/users";

    private ExternalUsersServiceClient() {
    }

    public static Set<ExternalUser> getUsers() {
        GenericType<Set<ExternalUser>> externalUsersListType = new GenericType<>() {
        };
        return ClientBuilder.newClient().target(REST_TARGET_URL).request().get(externalUsersListType);
    }

    public static ExternalUser getUserById(String id) {
        return ClientBuilder.newClient().target(REST_TARGET_URL).path("{id}").resolveTemplate("id", id).request()
                .get(ExternalUser.class);
    }

}
