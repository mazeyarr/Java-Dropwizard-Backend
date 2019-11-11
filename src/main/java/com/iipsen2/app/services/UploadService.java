package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.interfaces.abstracts.UploadPaths;
import com.iipsen2.app.interfaces.enums.UploadType;
import com.iipsen2.app.models.Upload;
import liquibase.util.file.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

public class UploadService {
    private static DAO UploadDAO;

    public UploadService(DAO UploadDAO) {
        UploadService.UploadDAO = UploadDAO;
    }

    public Upload saveFileToFolder(UploadType type, long entity_id, InputStream fileInputStream) {
        switch (type) {
            case PROJECT:
                HashMap<String, Object> saveResult = saveProjectFileToFolder(fileInputStream);
                if (!(boolean) saveResult.get("error")) {
                    long uploadId = UploadDAO.insertToUploads(
                            (String) saveResult.get("filename"),
                            (String) saveResult.get("path"),
                            (String) saveResult.get("mimeType"),
                            (String) saveResult.get("extension"),
                            entity_id
                    );

                    return UploadDAO.findUploadById(uploadId);
                } else {
                    return null;
                }

            case AVATAR:
            default:
                return null;
        }
    }

    public Upload updateFileInFolder(UploadType type, long entity_id, String currentFilename, InputStream newFile) {
        switch (type) {
            case PROJECT:
                HashMap<String, Object> removeResult = removeProjectFile(currentFilename);

                if (!(boolean) removeResult.get("error")) {
                    HashMap<String, Object> saveResult = saveProjectFileToFolder(newFile);

                    if (!(boolean) saveResult.get("error")) {
                        UploadDAO.updateUpload(
                                entity_id,
                                (String) saveResult.get("filename"),
                                (String) saveResult.get("path"),
                                (String) saveResult.get("mimeType"),
                                (String) saveResult.get("extension")
                        );

                        return UploadDAO.findUploadById(entity_id);
                    }
                }
                return null;

            case AVATAR:
            default:
                return null;
        }
    }

    private HashMap<String, Object> saveProjectFileToFolder(InputStream fileInputStream) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            String filename = UUID.randomUUID() + ".pdf";
            String outputPath = UploadPaths.generateProjectPath(filename);

            int read;
            byte[] bytes = new byte[1024];


            OutputStream out = new FileOutputStream(new File(outputPath));

            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.flush();
            out.close();

            Path path = new File(outputPath).toPath();
            String mimeType = Files.probeContentType(path);
            String extension = FilenameUtils.getExtension(outputPath);

            result.put("error", false);
            result.put("filename", filename);
            result.put("path", UploadPaths.PROJECT_PATH);
            result.put("mimeType", mimeType);
            result.put("extension", extension);

            return result;
        } catch (IOException e) {
            e.printStackTrace();

            result.put("error", true);

            return result;
        }
    }

    private HashMap<String, Object> removeProjectFile(String filename) {
        HashMap<String, Object> result = new HashMap<>();

        try{

            File file = new File(UploadPaths.generateProjectPath(filename));

            if(file.delete()){
                result.put("error", false);
            }else{
                result.put("error", true);
            }

        }catch(Exception e){
            result.put("error", true);
            e.printStackTrace();
        }

        return result;
    }
}
