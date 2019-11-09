package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Education Model
 *
 * @author Mazeyar Rezaei
 * @since 17-10-2019
 */
public class Education {
    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private Institute institute;

    public Education(long id, String title, Institute institute) {
        this.id = id;
        this.title = title;
        this.institute = institute;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Institute getInstitute() {
        return institute;
    }
}
