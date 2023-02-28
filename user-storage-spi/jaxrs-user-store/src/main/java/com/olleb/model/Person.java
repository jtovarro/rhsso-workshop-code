package com.olleb.model;

public class Person {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public Person(String id, String firstName, String lastname, String email, String password){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastname;
        this.email=email;
        this.password=password;
    }

}
