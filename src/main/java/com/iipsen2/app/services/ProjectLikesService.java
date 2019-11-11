package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.interfaces.enums.LikeType;
import com.iipsen2.app.models.ProjectLikes;

import java.util.HashMap;
import java.util.List;

public class ProjectLikesService {
    private static DAO ProjectLikesDAO;

    public ProjectLikesService(DAO ProjectDAO) {
        ProjectLikesService.ProjectLikesDAO = ProjectDAO;
    }

    public static List<ProjectLikes> getProjectLikes(long project_id) {
        return ProjectLikesDAO.getProjectLikes(project_id);
    }

    public static HashMap<String, Integer> handleProjectLike(long project_id, LikeType likeType) {
        if (hasUserLikedProject(project_id)) {
            if (isSameTypeOfLike(likeType, project_id, UserService.getAuthUser().getId())) {
                ProjectLikesDAO.deleteProjectLikeOfUser(
                        project_id,
                        UserService.getAuthUser().getId()
                );
            } else {
                ProjectLikesDAO.updateProjectLikeOfUser(
                        likeType,
                        project_id,
                        UserService.getAuthUser().getId()
                );
            }
        } else {
            ProjectLikesDAO.insertToProjectsLikes(
                    likeType,
                    project_id,
                    UserService.getAuthUser().getId()
            );
        }

        HashMap<String, Integer> totalLikes = new HashMap<>();
        totalLikes.put("totalLikes", ProjectLikesDAO.getProjectTotalLikes(project_id));

        return totalLikes;
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

    public static boolean isSameTypeOfLike(LikeType like, long projectId, long userId) {
        ProjectLikes projectLike = ProjectLikesDAO.getProjectLike(projectId, userId);

        return projectLike.getLikeType().equals(like);
    }
}
