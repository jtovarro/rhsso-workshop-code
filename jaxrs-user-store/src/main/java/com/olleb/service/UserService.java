package com.olleb.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.olleb.model.User;
import com.olleb.model.User.UserBuilder;

@ApplicationScoped
public class UserService {

    private final Map<String, User> userMap = Map.of(
            "1", new UserBuilder("1", "nata")
                    .setFirstName("Nata")
                    .setEmail("nata@example.com")
                    .setPassword("natapassword")
                    .build(),
            "2", new UserBuilder("2", "freud")
                    .setFirstName("Freud")
                    .setEmail("freud@example.com")
                    .setPassword("freudpassword")
                    .build(),
            "3", new UserBuilder("3", "mao")
                    .setFirstName("Mao")
                    .setEmail("mao@example.com")
                    .setPassword("maopassword")
                    .build(),
            "4", new UserBuilder("4", "dark")
                    .setFirstName("Dark")
                    .setEmail("dark@example.com")
                    .setPassword("darkpassword")
                    .build(),
            "5", new UserBuilder("5", "abel")
                    .setFirstName("Abel")
                    .setEmail("abel@example.com")
                    .setPassword("abelpassword")
                    .build(),
            "6", new UserBuilder("6", "flash")
                    .setFirstName("Flash")
                    .setEmail("flash@example.com")
                    .setPassword("flashpassword")
                    .build()

    );

    public Set<User> getUsers() {
        return userMap.values().stream().collect(Collectors.toSet());
    }

    public Optional<User> getUserById(final String id) {
        return Optional.of(userMap.get(id));
    }

}