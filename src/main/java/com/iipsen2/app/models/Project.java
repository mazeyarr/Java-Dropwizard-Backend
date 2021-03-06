package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Project Model
 *
 * @author Mazeyar Rezaei
 * @since 17-10-2019
 */
public class Project {
    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String language;

    @JsonProperty
    private String tags;

    @JsonProperty
    private String catagory;

    @JsonProperty
    private User createdBy;

    @JsonProperty
    private Education education;

    @JsonProperty
    private Upload resource;

    @JsonProperty
    private List<ProjectLikes> likes;

    @JsonProperty
    private int totalLikes;

    public Project() {
    }

    public Project(
            long id,
            String title,
            String language,
            String tags,
            String category,
            User createdBy,
            Education education,
            Upload resource
    ) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.tags = tags;
        this.catagory = category;
        this.createdBy = createdBy;
        this.education = education;
        this.resource = resource;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getTags() {
        return tags;
    }

    public String getCategory() {
        return catagory;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Education getEducation() {
        return education;
    }

    public Upload getResource() {
        return resource;
    }

    public List<ProjectLikes> getLikes() {
        return likes;
    }

    public void setLikes(List<ProjectLikes> likes) {
        this.likes = likes;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }
}
