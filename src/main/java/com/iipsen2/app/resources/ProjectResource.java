package com.iipsen2.app.resources;

import com.iipsen2.app.models.Project;
import com.iipsen2.app.services.ProjectLikesService;
import com.iipsen2.app.services.ProjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/project")
public class ProjectResource {
    private ProjectService projectService;
    private ProjectLikesService projectLikesService;

    public ProjectResource(ProjectService projectService, ProjectLikesService projectLikesService) {
        this.projectService = projectService;
        this.projectLikesService = projectLikesService;
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Project> getProjects(
            @QueryParam("limit") int limit
    ) {
        if (limit < 1)
            return ProjectService.getProjects();
        else
            return ProjectService.getProjects(limit);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Project getProjectAction(
            @PathParam("id") long id
    ) {
        return ProjectService.getProject(id);
    }

    @GET
    @Path("/{id}/resource")
    public Response getProjectResource(
            @PathParam("id") long id
    ) {
        Project project = ProjectService.getProject(id);

        return Response
                .ok(ProjectService.getProjectResource(project), MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = " + project.getResource().getFilename())
                .build();
    }

}
