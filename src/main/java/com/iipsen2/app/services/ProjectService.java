package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.interfaces.abstracts.UploadPaths;
import com.iipsen2.app.models.Project;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProjectService {
    private static DAO ProjectDAO;

    public ProjectService(DAO ProjectDAO) {
        ProjectService.ProjectDAO = ProjectDAO;
    }

    public static Project getProject(long id) {
        return ProjectDAO.findProjectById(id);
    }

    public static StreamingOutput getProjectResource(final Project project) {
        return new StreamingOutput()
        {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException
            {
                try
                {
                    Path path = Paths.get(project.getResource().getFullPath());

                    byte[] data = Files.readAllBytes(path);

                    output.write(data);
                    output.flush();
                }
                catch (Exception e)
                {
                    throw new WebApplicationException("File Not Found !!");
                }
            }
        };
    }

    public static List<Project> getProjects() {
        return ProjectDAO.getProjects();
    }

    public static List<Project> getProjects(int limit) {
        return ProjectDAO.getProjects(limit);
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

    public static Project updateProject(
            long projectId,
            String title,
            String language,
            String tags,
            String category
    ) {
        ProjectDAO.updateProject(projectId, title, language, tags, category);

        return ProjectDAO.findProjectById(projectId);
    }
}
