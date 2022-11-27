package com.olleb;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import com.olleb.User.UserBuilder;

// https://www.keycloak.org/docs/18.0/server_development/index.html#_provider_capability_interfaces
public class DemoUserStorageProvider
        implements UserStorageProvider, UserLookupProvider, UserQueryProvider, CredentialInputValidator {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

    private final KeycloakSession keycloakSession;
    private final ComponentModel componentModel;

    public DemoUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel) {
        this.keycloakSession = keycloakSession;
        this.componentModel = componentModel;
    }

    @Override
    public void close() {
        // ok
    }

    // TODO catch the results avoid multiple calls

    @Override
    public List<UserModel> getUsers(RealmModel realm) {
        Set<ExternalUser> externalUsers = ExternalUsersServiceClient.getUsers();
        return externalUsers.stream().map(e -> this.userModelMapper(e, realm)).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
        Set<ExternalUser> externalUsers = ExternalUsersServiceClient.getUsers();
        return externalUsers.stream().collect(Collectors.toList()).subList(firstResult, maxResults).stream()
                .map(e -> this.userModelMapper(e, realm)).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> searchForUser(String search, RealmModel realm) {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm) {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm, int firstResult,
            int maxResults) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group, int firstResult, int maxResults) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserModel> searchForUserByUserAttribute(String attrName, String attrValue, RealmModel realm) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserModel getUserById(String id, RealmModel realmModel) {
        // StorageId is used for referencing users that are stored outside of Keycloak
        String externalId = StorageId.externalId(id);
        ExternalUser externalUser = ExternalUsersServiceClient.getUserById(externalId);
        return userModelMapper(externalUser, realmModel);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        Set<ExternalUser> users = ExternalUsersServiceClient.getUsers();
        ExternalUser externalUser = findByUsername(users, username).orElseThrow();
        return userModelMapper(externalUser, realm);
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        Set<ExternalUser> users = ExternalUsersServiceClient.getUsers();
        ExternalUser externalUser = findByEmail(users, email).orElseThrow();
        return userModelMapper(externalUser, realm);
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return credentialType.equals(PasswordCredentialModel.TYPE);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return supportsCredentialType(credentialType);
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        if (!supportsCredentialType(credentialInput.getType())) {
            return false;
        }
        StorageId storageId = new StorageId(user.getId());
        ExternalUser externalUser = ExternalUsersServiceClient.getUserById(storageId.getExternalId());
        if (externalUser != null) {
            String password = externalUser.getPassword();
            return password != null && password.equals(credentialInput.getChallengeResponse());
        }
        return false;
    }

    // TODO for now, the search stuff will be done here instead of at rest api

    private Optional<ExternalUser> findByUsername(Set<ExternalUser> users, String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    private Optional<ExternalUser> findByEmail(Set<ExternalUser> users, String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    //

    private UserModel userModelMapper(ExternalUser externalUser, RealmModel realmModel) {
        return new UserBuilder(keycloakSession, realmModel, componentModel, externalUser.getUsername(), externalUser.getExternalId())
                .setEmail(externalUser.getEmail())
                .setFirstName(externalUser.getFirstName())
                .setLastName(externalUser.getLastName())
                .build();
    }

}
