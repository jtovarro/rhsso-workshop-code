package com.olleb;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class DemoUserStorageProviderFactory implements UserStorageProviderFactory<DemoUserStorageProvider> {

    @Override
    public DemoUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new DemoUserStorageProvider(keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return "demo-user-provider";
    }

}