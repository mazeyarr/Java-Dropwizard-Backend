package com.iipsen2.app.resources;

import com.iipsen2.app.filters.bindings.AuthBinding;
import com.iipsen2.app.models.Project;
import com.iipsen2.app.services.UploadService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.UUID;


@Path("/uploads")
public class UploadResource {
    private UploadService UploadService;

    public UploadResource(UploadService uploadService) {
        this.UploadService = uploadService;
    }

    @POST
    @AuthBinding
    @Path("/project")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Project postUploadProjectAction(
            @HeaderParam("Authorization") final String authToken,
            @NotNull @FormDataParam("title") String title,
            @NotNull @FormDataParam("language") String language,
            @NotNull @FormDataParam("fieldOfStudy") String fieldOfStudy,
            @NotNull @FormDataParam("tags") String tags,
            @NotNull @FormDataParam("category") String category,
            @NotNull @FormDataParam("project") final InputStream projectFileInputStream,
            @NotNull @FormDataParam("project") FormDataContentDisposition projectFileMetaData
    ) {
        try {
            Project project = new Project(
                    title,
                    language,
                    fieldOfStudy,
                    tags,
                    category
            );

            project.setFilename(
                    UUID.randomUUID().toString()
            );

            int read;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File("src/main/resources/uploads/" + project.getFilename() + ".pdf"));

            while ((read = projectFileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.flush();
            out.close();

            return project;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Project();
    }
}