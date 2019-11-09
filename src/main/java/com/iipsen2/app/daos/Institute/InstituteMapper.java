package com.iipsen2.app.daos.Institute;

import com.iipsen2.app.models.Institute;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps the attributes from the Institute table in the database to Java Objects
 *
 * @author Mazeyar Reazaei
 * @since 17-10-2019
 */
public class InstituteMapper implements ResultSetMapper<Institute>{
    public Institute map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new Institute(
                r.getLong("institute_id") ,
                r.getString("name"),
                r.getString("location")
        );
    }
}
