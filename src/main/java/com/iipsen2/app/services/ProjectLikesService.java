package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.interfaces.enums.LikeType;
import com.iipsen2.app.models.Project;
import com.iipsen2.app.models.ProjectLikes;
import java.util.List;

public class ProjectLikesService {
    private static DAO ProjectLikesDAO;

    public ProjectLikesService(DAO ProjectDAO) {
        ProjectLikesService.ProjectLikesDAO = ProjectDAO;
    }

    public static List<ProjectLikes> getProjectLikes(long project_id) {
        return ProjectLikesDAO.getProjectLikes(project_id);
    }

    public static int handleProjectLike(long project_id, LikeType likeType) {
        if (hasUserLikedProject(project_id)) {
            // TODO: if is same like type  this if would fire a delete query
            ProjectLikesDAO.updateProjectLikeOfUser(
                    likeType,
                    project_id,
                    UserService.getAuthUser().getId() // TODO: THIS IS NULL....
            );
        } else {
            ProjectLikesDAO.insertToProjectsLikes(
                    likeType,
                    project_id,
                    UserService.getAuthUser().getId()
            );
        }

        return ProjectLikesDAO.getProjectTotalLikes(project_id);
    }

    public static int getProjectTotalLikes(long project_id) {
        return ProjectLikesDAO.getProjectTotalLikes(project_id);
    }

    public static boolean hasUserLikedProject(long project_id) {
        int amountUserLikedProject = ProjectLikesDAO.getCountUserLikedProject(
                project_id,
                UserService.getAuthUser().getId()
        );

        return amountUserLikedProject == 1;
    }
}
