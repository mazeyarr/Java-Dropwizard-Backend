package com.iipsen2.app.daos.Project;

import com.iipsen2.app.models.Project;
import com.iipsen2.app.models.User;
import com.iipsen2.app.services.EducationService;
import com.iipsen2.app.services.UserService;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements ResultSetMapper<Project> {
    @Override
    public Project map(int i, ResultSet r, StatementContext ctx) throws SQLException {
        return new Project(
                r.getLong("id"),
                r.getString("title"),
                r.getString("language"),
                r.getString("tags"),
                r.getString("category"),
                UserService.getUserById(r.getLong("created_user_id")),
                EducationService.getEducation(r.getLong("education_id"))
        );
    }
}
