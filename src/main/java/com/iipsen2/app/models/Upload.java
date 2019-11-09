package com.iipsen2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Upload Model
 *
 * @author Mazeyar Rezaei
 * @since 18-10-2019
 */
public class Upload {
    @JsonProperty
    private long id;

    @JsonProperty
    private String filename;

    @JsonProperty
    private String path;

    @JsonProperty
    private String mime;

    @JsonProperty
    private String extension;

    public Upload(long id, String filename, String path, String mime, String extension) {
        this.id = id;
        this.filename = filename;
        this.path = path;
        this.mime = mime;
        this.extension = extension;
    }


    public long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getPath() {
        return path;
    }

    public String getFullPath() {
        return this.getPath() + this.getFilename();
    }

    public String getMime() {
        return mime;
    }

    public String getExtension() {
        return extension;
    }
}
