package com.iipsen2.app.daos.Education;

import com.iipsen2.app.models.Education;
import com.iipsen2.app.models.UserRoles;
import com.iipsen2.app.services.InstituteService;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps the attributes from the Education table in the database to Java Objects
 *
 * @author Mazeyar Reazaei
 * @since 17-10-2019
 */
public class EducationMapper implements ResultSetMapper<Education>{
    public Education map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        // TODO: change this mapper so that it uses joins instead of a service to join @mazeyar
        return new Education(
                r.getLong("education_id") ,
                r.getString("title"),
                InstituteService.getInstitute(r.getLong("institute_id"))
        );
    }
}
