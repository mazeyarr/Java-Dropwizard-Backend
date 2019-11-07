package com.iipsen2.app.daos.Institute;

import com.iipsen2.app.models.Education;
import com.iipsen2.app.models.Institute;
import com.iipsen2.app.models.UserRoles;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstituteMapper implements ResultSetMapper<Institute>{
    public Institute map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new Institute(
                r.getLong("institute_id") ,
                r.getString("name"),
                r.getString("location")
        );
    }
}
