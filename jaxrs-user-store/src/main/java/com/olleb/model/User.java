package com.olleb.model;

public class User {

    private final String userId;

    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;

    private User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.username = userBuilder.username;
        this.email = userBuilder.email;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.password = userBuilder.password;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class UserBuilder {
        private final String userId;
        private final String username;

        private String email;
        private String firstName;
        private String lastName;
        private String password;

        public User build() {
            return new User(this);
        }

        public UserBuilder(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setPassword(String password){
            this.password = password;
            return this;
        }
    }

}
