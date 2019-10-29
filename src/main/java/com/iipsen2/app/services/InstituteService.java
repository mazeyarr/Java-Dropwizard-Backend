package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.models.Institute;
import com.iipsen2.app.models.Project;

public class InstituteService {
    public static DAO InstituteDAO;

    public InstituteService(DAO InstituteDAO) {
        InstituteService.InstituteDAO = InstituteDAO;
    }

    public static Institute getInstitute(long id) {
        return InstituteDAO.findInstituteById(id);
    }
}
