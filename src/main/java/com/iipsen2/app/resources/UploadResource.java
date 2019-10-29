package com.iipsen2.app.resources;

import com.iipsen2.app.filters.bindings.AuthBinding;
import com.iipsen2.app.interfaces.enums.UploadType;
import com.iipsen2.app.models.Project;
import com.iipsen2.app.models.Upload;
import com.iipsen2.app.services.ProjectService;
import com.iipsen2.app.services.UploadService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.UUID;


@Path("/upload")
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
    public Upload postUploadProjectAction(
            @NotNull @FormDataParam("title") String title,
            @NotNull @FormDataParam("language") String language,
            @NotNull @FormDataParam("fieldOfStudy") String fieldOfStudy,
            @NotNull @FormDataParam("tags") String tags,
            @NotNull @FormDataParam("category") String category,
            @NotNull @FormDataParam("project") final InputStream projectFileInputStream,
            @NotNull @FormDataParam("project") FormDataContentDisposition projectFileMetaData
    ) {
        Project newProject = ProjectService.createProject(title, language, tags, category);

        return UploadService.saveFileToFolder(UploadType.PROJECT, newProject.getId(), projectFileInputStream);
    }
}