package com.iipsen2.app.resources;

import com.iipsen2.app.models.User;
import com.iipsen2.app.services.UserService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
    private UserService UserService;

    public UserResource(UserService userService) {
        this.UserService = userService;
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public User postLoginAction(
            @QueryParam("username") String username,
            @QueryParam("password") String password
    ) {
        User authUser = UserService.getAuthUser(username, password);

        return authUser;
    }

    @POST
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    public User postCreateAction(
            @QueryParam("username") String username,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("password") String password,
            @QueryParam("role") String role
    ) {
        User user = UserService.createUser(
                username,
                password,
                firstname,
                lastname,
                role
        );

        return user;
    }
}
