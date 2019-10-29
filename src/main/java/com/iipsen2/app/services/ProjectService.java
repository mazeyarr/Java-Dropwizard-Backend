package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.models.Project;

import java.io.InputStream;
import java.util.UUID;

public class ProjectService {
    private static DAO ProjectDAO;

    public ProjectService(DAO ProjectDAO) {
        ProjectService.ProjectDAO = ProjectDAO;
    }

    public static Project getProject(long id) {
        return ProjectDAO.findProjectById(id);
    }

    public static Project createProject(
            String title,
            String language,
            String tags,
            String category
    ) {
        long projectId = ProjectDAO.insertToProjects(
                title,
                language,
                tags,
                category,
                UserService.getAuthUser().getId(),
                EducationService.getEducation(1).getId()
        );

        return ProjectService.getProject(projectId);
    }
}
