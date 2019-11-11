package com.iipsen2.app.daos.ProjectLikes;

import com.iipsen2.app.interfaces.enums.LikeType;
import com.iipsen2.app.interfaces.enums.LikeTypeTranslator;
import com.iipsen2.app.models.Project;
import com.iipsen2.app.models.ProjectLikes;
import com.iipsen2.app.models.Upload;
import com.iipsen2.app.models.User;
import com.iipsen2.app.services.EducationService;
import com.iipsen2.app.services.UserService;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps the attributes from the ProjectLikes table in the database to Java Objects
 * Joins Project and User Objects
 * @author Mazeyar Reazaei
 * @since 05-11-2019
 */
public class ProjectLikesMapper implements ResultSetMapper<ProjectLikes> {
    @Override
    public ProjectLikes map(int i, ResultSet r, StatementContext ctx) throws SQLException {
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
                        r.getString("extension"),
                        r.getLong("project_id")
                )
        );

        User user = new User(
                r.getLong("user_id"),
                r.getString("username"),
                r.getString("password"),
                r.getString("firstname"),
                r.getString("lastname")
        );

        return new ProjectLikes(
                r.getLong("project_likes_id"),
                LikeType.valueOf(r.getString("like_type")),
                user,
                project
        );
    }

}
