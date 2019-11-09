package com.iipsen2.app.daos.User;

import com.iipsen2.app.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps the attributes from the Users table in the database to Java Objects
 * Joins likes to this object
 *
 * @author Mazeyar Reazaei
 * @since 17-10-2019
 */
public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(
                r.getLong("user_id"),
                r.getString("username"),
                r.getString("password"),
                r.getString("firstname"),
                r.getString("lastname")
        );
    }
}
