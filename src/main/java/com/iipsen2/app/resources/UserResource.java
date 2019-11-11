package com.iipsen2.app.resources;

import com.iipsen2.app.MainService;
import com.iipsen2.app.filters.bindings.AuthBinding;
import com.iipsen2.app.models.User;
import com.iipsen2.app.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
    private UserService UserService;

    public UserResource(UserService userService) {
        this.UserService = userService;
    }

    /**
     * Client lands on this endpoint, using the username and password combination. And sends all the data
     * to the UserService.
     *
     * @author Joeri Duijkren
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postLoginAction(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User authUser = UserService.getAuthUser(username, password);

        if (authUser != null)
            authUser.setJwt(
                    MainService.tokenProvider
                            .generateToken(authUser.getId())
            );

        return authUser;
    }

    @POST
    @AuthBinding
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postCreateAction(
            @FormParam("username") String username,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("password") String password,
            @FormParam("role") String role
    ) {

        return UserService.createUser(
                username,
                password,
                firstname,
                lastname,
                role
        );
    }
}
