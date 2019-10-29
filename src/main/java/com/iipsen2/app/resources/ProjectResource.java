package com.iipsen2.app.resources;

import com.iipsen2.app.models.Project;
import com.iipsen2.app.services.ProjectService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/project")
public class ProjectResource {
    private ProjectService projectService;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Project getProjectAction(
            @QueryParam("id") long id
    ) {
        return ProjectService.getProject(id);
    }

}
