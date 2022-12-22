package com.olleb;

import java.util.Optional;

import org.keycloak.Config.Scope;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

/**
 * @author Àngel Ollé Blázquez
 */
public class DemoUserStorageProviderFactory implements UserStorageProviderFactory<DemoUserStorageProvider> {

    private ExternalUsersService service;

    @Override
    public DemoUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new DemoUserStorageProvider(keycloakSession, componentModel, service);
    }

    @Override
    public String getId() {
        return "demo-user-provider";
    }

    @Override
    public void init(Scope config) {
        String host = Optional
                .ofNullable(System.getenv("USER_STORAGE_CUSTOM_SPI_TARGET_HOST"))
                .orElse(config.get("target-host"));

        service = new ExternalUsersService(host);
    }

}
