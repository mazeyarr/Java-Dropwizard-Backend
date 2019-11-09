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

/**
 * Authentication Filter that prevents users to use resources that they are not permitted for
 *
 * @author Mazeyar Rezaei
 * @since 17-10-2019
 */
@Provider
@AuthBinding
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        // Check if the user gave a Authorization key in the header
        if (!context.getHeaders().containsKey("Authorization")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        String token = context.getHeaders().getFirst("Authorization");

        // Check if the token is not empty
        if (token.equals("")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        System.err.println(MainService.tokenProvider.verifyToken(token));

        // Validate the token
        if (!MainService.tokenProvider.verifyToken(token)) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        // Save user in current session
        long id = MainService.tokenProvider.getDecodedJWT(token).getClaim("user_id").asLong();
        UserService.setAuthUser(
                UserService.getUserById(id)
        );
    }
}
