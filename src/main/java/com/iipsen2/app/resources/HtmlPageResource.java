package com.iipsen2.app.resources;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.iipsen2.app.MainService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;

@Path("/")
public class HtmlPageResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response index() {
        String pageContent = "";

        try {
            URL clientPage = Resources.getResource("index.html");
            pageContent = Resources.toString(clientPage, Charsets.UTF_8);
        } catch (IOException e) {
            return Response.serverError().build();
        }

        return Response.ok(pageContent).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response version() {
        return Response
                .status(Response.Status.OK)
                .entity(MainService.getVersion())
                .build();
    }
}
