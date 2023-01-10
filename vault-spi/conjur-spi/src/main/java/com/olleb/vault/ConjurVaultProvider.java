package com.olleb.vault;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.keycloak.vault.DefaultVaultRawSecret;
import org.keycloak.vault.VaultProvider;
import org.keycloak.vault.VaultRawSecret;

import com.cyberark.conjur.api.Conjur;

/**
 * @author Àngel Ollé Blázquez
 */
public class ConjurVaultProvider implements VaultProvider {

    Conjur conjur;

    public ConjurVaultProvider(Conjur conjur) {
        this.conjur = conjur;
    }

    @Override
    public VaultRawSecret obtainSecret(String vaultSecretId) {
        String secret = conjur.variables().retrieveSecret(vaultSecretId);
        ByteBuffer byteBuffer = ByteBuffer.wrap(secret.getBytes(StandardCharsets.UTF_8));
        return DefaultVaultRawSecret.forBuffer(Optional.of(byteBuffer));
    }

    @Override
    public void close() {
        // no-op
    }

}
