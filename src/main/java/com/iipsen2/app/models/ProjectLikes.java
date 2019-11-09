package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iipsen2.app.interfaces.enums.LikeType;

/**
 * ProjectLikes Model
 *
 * @author Mazeyar Rezaei
 * @since 05-11-2019
 */
public class ProjectLikes {
    @JsonProperty
    private long id;

    @JsonProperty
    private LikeType likeType;

    @JsonProperty
    private User users;

    @JsonProperty
    private Project project;

    public ProjectLikes(long id, LikeType likeType, User users, Project project) {
        this.id = id;
        this.likeType = likeType;
        this.users = users;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public LikeType getLikeType() {
        return likeType;
    }

    public User getUsers() {
        return users;
    }

    public Project getProject() {
        return project;
    }
}
