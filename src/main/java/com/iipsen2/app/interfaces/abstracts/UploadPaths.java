package com.iipsen2.app.interfaces.abstracts;

/**
 * Contains constants of upload paths
 */
public abstract class UploadPaths {
    public static final String AVATAR_PATH = "src/main/resources/uploads/avatars/";
    public static final String PROJECT_PATH = "src/main/resources/uploads/projects/";

    public static String generateProjectPath(String filename) {
        return PROJECT_PATH + filename;
    }
}
