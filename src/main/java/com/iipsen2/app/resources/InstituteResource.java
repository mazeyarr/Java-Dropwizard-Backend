package com.iipsen2.app.resources;

import com.iipsen2.app.models.Education;
import com.iipsen2.app.models.Institute;
import com.iipsen2.app.services.EducationService;
import com.iipsen2.app.services.InstituteService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/institute")
public class InstituteResource {
    private InstituteService instituteService;

    public InstituteResource(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Institute getInstituteAction(
            @QueryParam("id") long id
    ) {
        return InstituteService.getInstitute(id);
    }

}
