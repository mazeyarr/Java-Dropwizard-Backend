package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String language;

    @JsonProperty
    private String fieldOfStudy;

    @JsonProperty
    private String tags;

    @JsonProperty
    private String catagory;

    @JsonProperty
    private String filename;

    public Project() {
    }

    public Project(
            String title,
            String language,
            String fieldOfStudy,
            String tags,
            String category
    ) {
        this.title = title;
        this.language = language;
        this.fieldOfStudy = fieldOfStudy;
        this.tags = tags;
        this.catagory = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
