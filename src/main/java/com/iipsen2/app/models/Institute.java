package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Institute Model
 *
 * @author Mazeyar Rezaei
 * @since 17-10-2019
 */
public class Institute {
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String location;

    public Institute(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
