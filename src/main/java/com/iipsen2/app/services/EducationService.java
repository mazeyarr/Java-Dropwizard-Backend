package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.models.Education;
import com.iipsen2.app.models.Project;

public class EducationService {
    public static DAO EducationDAO;

    public EducationService(DAO ProjectDAO) {
        EducationService.EducationDAO = ProjectDAO;
    }

    public static Education getEducation(long id) {
        return EducationDAO.findEducationById(id);
    }
}
