package com.iipsen2.app.filters;

import com.iipsen2.app.MainService;
import com.iipsen2.app.filters.bindings.AuthBinding;
import com.iipsen2.app.services.UserService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@AuthBinding
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (!context.getHeaders().containsKey("Authorization")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        String token = context.getHeaders().getFirst("Authorization");

        if (token.equals("")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        System.err.println(MainService.tokenProvider.verifyToken(token));

        if (!MainService.tokenProvider.verifyToken(token)) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        long id = MainService.tokenProvider.getDecodedJWT(token).getClaim("user_id").asLong();
        UserService.setAuthUser(
                UserService.getUserById(id)
        );
    }
}