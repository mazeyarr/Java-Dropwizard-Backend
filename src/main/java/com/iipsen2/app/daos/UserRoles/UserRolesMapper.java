package com.iipsen2.app.daos.UserRoles;

import com.iipsen2.app.models.User;
import com.iipsen2.app.models.UserRoles;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRolesMapper implements ResultSetMapper<UserRoles>{
    public UserRoles map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new UserRoles(
                r.getLong("id") ,
                r.getLong("user_id"),
                r.getString("role")
        );
    }
}
