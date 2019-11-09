package com.iipsen2.app.interfaces.abstracts;

public abstract class UploadPaths {
    public static final String AVATAR_PATH = "src/main/resources/uploads/avatars/";
    public static final String PROJECT_PATH = "src/main/resources/uploads/projects/";

    public static String generatePath(String filename) {
        return PROJECT_PATH + filename;
    }
}