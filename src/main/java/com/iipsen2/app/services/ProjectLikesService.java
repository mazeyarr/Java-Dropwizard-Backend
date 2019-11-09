package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
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

    public static int getProjectTotalLikes(long project_id) {
        return ProjectLikesDAO.getProjectTotalLikes(project_id);
    }
}
