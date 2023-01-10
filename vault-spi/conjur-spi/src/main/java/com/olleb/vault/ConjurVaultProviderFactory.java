package com.olleb.vault;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.vault.VaultProvider;
import org.keycloak.vault.VaultProviderFactory;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Credentials;

/**
 * @author Àngel Ollé Blázquez
 */
public class ConjurVaultProviderFactory implements VaultProviderFactory {

    // private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    private static final String PROVIDER_ID = "conjur-vault";

    private Credentials credentials;

    @Override
    public VaultProvider create(KeycloakSession session) {
        Conjur conjur = new Conjur(credentials);
        return new ConjurVaultProvider(conjur);
    }

    @Override
    public void init(Scope config) {
        credentials = Credentials.fromSystemProperties();
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // no-op
    }

    @Override
    public void close() {
        // no-op
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

}
