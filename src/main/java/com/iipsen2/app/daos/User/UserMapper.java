package com.iipsen2.app.daos.User;

import com.iipsen2.app.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
