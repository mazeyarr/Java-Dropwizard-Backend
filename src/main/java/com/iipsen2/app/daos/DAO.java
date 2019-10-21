package com.iipsen2.app.daos;

import com.iipsen2.app.daos.User.UserMapper;
import com.iipsen2.app.daos.UserRoles.UserRolesMapper;
import com.iipsen2.app.models.User;
import com.iipsen2.app.models.UserRoles;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Main Data Access Object interface
 *
 * @author mazeyar
 * @since 16-11-2019
 */
public interface DAO {
    // User
    @SqlQuery("select * from users where username = :username and password = :password")
    @Mapper(UserMapper.class)
    User getAuthenticatedUser(@Bind("username") String username, @Bind("password") String password);

    @SqlQuery("select * from users as u LEFT JOIN user_roles as ur ON ur.user_id=u.id where u.id = :id")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("id") long id);

    @SqlUpdate("insert into users (username, password, firstname, lastname) values (:username, :password, :firstname, :lastname)")
    @GetGeneratedKeys
    long insertToUsers(
            @Bind("username") String username,
            @Bind("password") String password,
            @Bind("firstname") String firstname,
            @Bind("lastname") String lastname
    );

    // User Roles
    @SqlQuery("select id, user_id, role from user_roles where id = :id")
    @Mapper(UserRolesMapper.class)
    List<UserRoles> findUserRolesById(@Bind("id") long id);

    @SqlQuery("select id, user_id, role from user_roles where user_id = :user_id")
    @Mapper(UserRolesMapper.class)
    List<UserRoles> findUserRolesByUserId(@Bind("user_id") long userId);

    @SqlUpdate("insert into user_roles (user_id, role) values (:user_id, :role)")
    @GetGeneratedKeys
    long insertToUserRoles(
            @Bind("user_id") long userId,
            @Bind("role") String role
    );
}
