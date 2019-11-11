package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.models.User;
import com.iipsen2.app.models.UserRoles;

import java.util.List;

public class UserService {
    private static DAO UserDAO;
    private static User AuthUser;

    public UserService(DAO UserDAO) {
        UserService.UserDAO = UserDAO;
    }

    /**
     * Gets authenticated user using the credentials passed in the parameters.
     *
     * @author Joeri Duijkren
     * @param username
     * @param password
     * @return
     */
    public User getAuthUser(String username, String password) {
        User authUser = UserDAO.getAuthenticatedUser(username, password);

        if (authUser == null)
            return null;

        List<UserRoles> authUserRoles = UserDAO.findUserRolesByUserId(
                authUser.getId()
        );

        authUser.setRoles(authUserRoles);

        return authUser;
    }

    public static User getUserById(long id) {
        return UserDAO.findUserById(id);
    }

    public User createUser(
            String username,
            String password,
            String firstname,
            String lastname,
            String role
    ) {
        long userId  = UserDAO.insertToUsers(username, password, firstname, lastname);
        long userRoleId = UserDAO.insertToUserRoles(userId, role);

        User newUser = UserDAO.findUserById(userId);
        newUser.setRoles(UserDAO.findUserRolesByUserId(userId));

        return newUser;
    }

    public static User getAuthUser() {
        return AuthUser;
    }

    public static void setAuthUser(User authUser) {
        AuthUser = authUser;
    }
}
