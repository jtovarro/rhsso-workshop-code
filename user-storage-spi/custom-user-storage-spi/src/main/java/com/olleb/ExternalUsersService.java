package com.olleb;

import java.util.Optional;
import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 * @author Àngel Ollé Blázquez
 */
public class ExternalUsersService {

    private String endpoint;

    public ExternalUsersService(String host) {
        this.endpoint = "http://" + Optional.ofNullable(host).orElse("localhost:8081") + "/api/v1/users";
    }

    public Set<ExternalUser> getUsers() {
        GenericType<Set<ExternalUser>> externalUsersListType = new GenericType<>() {
        };
        return ClientBuilder.newClient().target(endpoint).request().get(externalUsersListType);
    }

    public ExternalUser getUserById(String id) {
        return ClientBuilder.newClient().target(endpoint).path("{id}").resolveTemplate("id", id).request()
                .get(ExternalUser.class);
    }

}
