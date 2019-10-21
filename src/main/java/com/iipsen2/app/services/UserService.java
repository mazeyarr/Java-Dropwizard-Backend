package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.models.User;
import com.iipsen2.app.models.UserRoles;

import java.util.List;

public class UserService {
    private DAO UserDAO;

    public UserService(DAO UserDAO) {
        this.UserDAO = UserDAO;
    }

    public User getAuthUser(String username, String password) {
        User authUser = UserDAO.getAuthenticatedUser(username, password);

        if (authUser == null)
            return new User();

        List<UserRoles> authUserRoles = UserDAO.findUserRolesByUserId(authUser.getId());
        authUser.setRoles(authUserRoles);

        return authUser;
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
}
