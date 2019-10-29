package com.iipsen2.app.resources;

import com.iipsen2.app.models.Education;
import com.iipsen2.app.models.Project;
import com.iipsen2.app.services.EducationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/education")
public class EducationResource {
    private EducationService educationService;

    public EducationResource(EducationService educationService) {
        this.educationService = educationService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Education getEducationAction(
            @QueryParam("id") long id
    ) {
        return EducationService.getEducation(id);
    }

}
