package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * User Model
 *
 * @author Mazeyar Rezaei
 * @since 17-10-2019
 */
public class User {
    @JsonProperty
    private long id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String firstname;

    @JsonProperty
    private String lastname;

    @JsonProperty
    private String password;

    @JsonProperty
    private String jwt;

    private List<UserRoles> roles;

    public User() {

    }

    public User(
            long id,
            String username,
            String password,
            String firstname,
            String lastname
    ) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setFirstname(firstname);
        this.setLastname(lastname);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }
}
