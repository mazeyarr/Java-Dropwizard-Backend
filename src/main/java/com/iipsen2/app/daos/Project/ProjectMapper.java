package com.iipsen2.app.daos.Project;

import com.iipsen2.app.models.Project;
import com.iipsen2.app.models.Upload;
import com.iipsen2.app.services.EducationService;
import com.iipsen2.app.services.ProjectLikesService;
import com.iipsen2.app.services.UserService;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps the attributes from the Project table in the database to Java Objects
 * Joins likes to this object
 *
 * @author Mazeyar Reazaei
 * @since 17-10-2019
 */
public class ProjectMapper implements ResultSetMapper<Project> {
    @Override
    public Project map(int i, ResultSet r, StatementContext ctx) throws SQLException {
        // TODO: change this mapper so that it uses joins instead of a service to join @mazeyar
        Project project = new Project(
                r.getLong("project_id"),
                r.getString("title"),
                r.getString("language"),
                r.getString("tags"),
                r.getString("category"),
                UserService.getUserById(r.getLong("created_user_id")),
                EducationService.getEducation(r.getLong("education_id")),
                new Upload(
                        r.getLong("upload_id"),
                        r.getString("filename"),
                        r.getString("path"),
                        r.getString("mime"),
                        r.getString("extension")
                )
        );

        project.setLikes(ProjectLikesService.getProjectLikes(project.getId()));
        project.setTotalLikes(ProjectLikesService.getProjectTotalLikes(project.getId()));

        return project;
    }
}
