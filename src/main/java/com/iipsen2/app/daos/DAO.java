package com.iipsen2.app.daos;

import com.iipsen2.app.daos.Education.EducationMapper;
import com.iipsen2.app.daos.Institute.InstituteMapper;
import com.iipsen2.app.daos.Project.ProjectMapper;
import com.iipsen2.app.daos.ProjectLikes.ProjectLikesMapper;
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
 * Main Data Access Object interface for al the query's
 *
 * @author mazeyar
 * @since 16-11-2019
 * @version 1.0
 */
public interface DAO {
    // User

    /**
     * Gets the authenticated user if the combination of username and password is correct
     *
     * @author Mazeyar Rezaei
     * @param username string
     * @param password string
     * @return User object
     */
    @SqlQuery("select * from users where username = :username and password = :password")
    @Mapper(UserMapper.class)
    User getAuthenticatedUser(@Bind("username") String username, @Bind("password") String password);

    /**
     * Finds a user by the id, runs a select query with the primary id key in the where clause
     *
     * @author Mazeyar Rezaei
     * @param id long primary key
     * @return User object
     * @since 16-11-2019
     * @version 1.0
     */
    @SqlQuery("select * from users as u LEFT JOIN user_roles as ur ON ur.user_id=u.user_id where u.user_id = :id")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("id") long id);

    /**
     * Inserts a user in the users table with all the required attributes.
     *
     * @param username string
     * @param password string
     * @param firstname string
     * @param lastname string
     * @return primary key (id) of inserted row
     */
    @SqlUpdate("insert into users (username, password, firstname, lastname) values (:username, :password, :firstname, :lastname)")
    @GetGeneratedKeys
    long insertToUsers(
            @Bind("username") String username,
            @Bind("password") String password,
            @Bind("firstname") String firstname,
            @Bind("lastname") String lastname
    );

    // User Roles
    /**
     * find the role of the user within the system by primary id.
     *
     * @author Mazeyar Rezaei
     * @param id long primary key
     * @return returns a list of user roles
     * @since 16-10-2019
     */
    @SqlQuery("select user_role_id, user_id, role from user_roles where user_role_id = :id")
    @Mapper(UserRolesMapper.class)
    List<UserRoles> findUserRolesById(@Bind("id") long id);

    /**
     * find the role of the user within the system by primary id of the users table user_id
     *
     * @author Mazeyar Rezaei
     * @param userId long foreign key
     * @return returns a list of user roles
     * @since 16-10-2019
     */
    @SqlQuery("select * from user_roles where user_id = :user_id")
    @Mapper(UserRolesMapper.class)
    List<UserRoles> findUserRolesByUserId(@Bind("user_id") long userId);

    /**
     * Inserts a role for the user in the user_role table.
     *
     * @author Mazeyar Rezaei
     * @param userId long foreign key
     * @param role string
     * @return primary key (id) of inserted row
     */
    @SqlUpdate("insert into user_roles (user_id, role) values (:user_id, :role)")
    @GetGeneratedKeys
    long insertToUserRoles(
            @Bind("user_id") long userId,
            @Bind("role") String role
    );

    // Institutes

    /**
     * finds the institute by running a select query with the primary id of the institute in the where clause
     *
     * @author Mazeyar Rezaei
     * @param id long primary key
     * @return Institute object
     * @since 17-10-2019
     * @version 1.0
     */
    @SqlQuery("select * from institutes where institute_id = :id")
    @Mapper(InstituteMapper.class)
    Institute findInstituteById(@Bind("id") long id);

    // Educations

    /**
     * finds the Education by running a select query with the primary id of the Education in the where clause
     *
     * @author Mazeyar Rezaei
     * @param id long primary key
     * @return Education object
     * @since 17-10-2019
     * @version 1.0
     */
    @SqlQuery("select * from educations where education_id = :id")
    @Mapper(EducationMapper.class)
    Education findEducationById(@Bind("id") long id);

    // Projects

    /**
     * finds the Project by running a select query with the primary id of the Project in the where clause
     *
     * @author Mazeyar Rezaei
     * @param id long primary key
     * @return Project object
     * @since 17-10-2019
     * @version 1.0
     */
    @SqlQuery("select * from projects as p left join uploads as u on u.project_id = p.project_id where p.project_id = :id")
    @Mapper(ProjectMapper.class)
    Project findProjectById(@Bind("id") long id);

    /**
     * Gets all the projects in the projects table
     *
     * @author Mazeyar Rezaei
     * @since 17-10-2019
     * @return list of Project objects
     * @version 1.0
     */
    @SqlQuery("select * from projects as p left join uploads as u on u.project_id = p.project_id")
    @Mapper(ProjectMapper.class)
    List<Project> getProjects();

    /**
     * (Overloaded method)
     * Gets a certain amount of projects in the projects table, by limiting the output in the query.
     *
     * @author Mazeyar Rezaei
     * @param limit integer
     * @since 17-10-2019
     * @return list of Project objects
     * @version 1.0
     */
    @SqlQuery("select * from projects as p left join uploads as u on u.project_id = p.project_id limit :limit")
    @Mapper(ProjectMapper.class)
    List<Project> getProjects(@Bind("limit") long limit);

    /**
     * Inserts a project in the projects table of the database.
     *
     * @author Mazeyar Rezaei
     * @since 17-10-2019
     * @param title string
     * @param language string
     * @param tags string
     * @param category string
     * @param createdByUserId foreign key User
     * @param educationId foreign key Education
     * @return primary key (id) of inserted row
     */
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

    // PROJECT LIKES

    /**
     * Gets al the likes associated with a Project object.
     *
     * @author Mazeyar Rezaei
     * @since 05-11-2019
     * @param project_id foreign key Project
     * @return List of ProjectLikes object
     */
    @SqlQuery("select * from projects_likes as pl left join users as u on pl.user_id = pl.user_id left join projects as p on pl.project_id = p.project_id left join uploads as up on up.project_id = p.project_id where pl.project_id = :project_id")
    @Mapper(ProjectLikesMapper.class)
    List<ProjectLikes> getProjectLikes(@Bind("project_id") long project_id);

    /**
     * Counts the rows of users that liked the project
     *
     * @author Mazeyar Rezaei
     * @since 05-11-2019
     * @param projectId foreign key Project
     * @return total amount of likes as an integer
     */
    @SqlQuery("select count(*) from projects_likes as pl where pl.project_id = :project_id")
    int getProjectTotalLikes(@Bind("project_id") long projectId);

    // Uploads

    /**
     * Gets the upload meta-data from the database
     *
     * @author Mazeyar Rezaei
     * @since 18-10-2019
     * @param id primary key
     * @return Upload object.
     */
    @SqlQuery("select * from uploads where upload_id = :id")
    @Mapper(UploadMapper.class)
    Upload findUploadById(@Bind("id") long id);

    /**
     * Inserts meta-data of a uploaded resource file in the uploads table of the database.
     *
     * @author Mazeyar Rezaei
     * @since 18-11-2019
     * @param filename string
     * @param path string
     * @param mime string
     * @param extension string
     * @param projectId foreign key Project
     * @return primary key (id) of inserted row
     */
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
