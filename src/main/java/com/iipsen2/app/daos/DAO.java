package com.iipsen2.app.daos;

import com.iipsen2.app.daos.Education.EducationMapper;
import com.iipsen2.app.daos.Institute.InstituteMapper;
import com.iipsen2.app.daos.Project.ProjectMapper;
import com.iipsen2.app.daos.Upload.UploadMapper;
import com.iipsen2.app.daos.User.UserMapper;
import com.iipsen2.app.daos.UserRoles.UserRolesMapper;
import com.iipsen2.app.models.*;
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

    // Institutes
    @SqlQuery("select * from institutes where id = :id")
    @Mapper(InstituteMapper.class)
    Institute findInstituteById(@Bind("id") long id);

    // Educations
    @SqlQuery("select * from educations where id = :id")
    @Mapper(EducationMapper.class)
    Education findEducationById(@Bind("id") long id);

    // Projects
    @SqlQuery("select * from projects where id = :id")
    @Mapper(ProjectMapper.class)
    Project findProjectById(@Bind("id") long id);

    @SqlUpdate("insert into projects (title, language, tags, category, created_user_id, education_id) values (:title, :language, :tags, :category, :created_user_id, :education_id)")
    @GetGeneratedKeys
    long insertToProjects(
            @Bind("title") String title,
            @Bind("language") String language,
            @Bind("tags") String tags,
            @Bind("category") String category,
            @Bind("created_user_id") long createdByUserId,
            @Bind("education_id") long educationId
    );

    // Uploads
    @SqlQuery("select * from uploads where id = :id")
    @Mapper(UploadMapper.class)
    Upload findUploadById(@Bind("id") long id);

    @SqlUpdate("insert into uploads (filename, path, mime, extension, project_id) values (:filename, :path, :mime, :extension, :project_id)")
    @GetGeneratedKeys
    long insertToUploads(
            @Bind("filename") String filename,
            @Bind("path") String path,
            @Bind("mime") String mime,
            @Bind("extension") String extension,
            @Bind("project_id") long projectId
    );
}
