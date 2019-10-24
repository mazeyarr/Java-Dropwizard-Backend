package com.iipsen2.app.services;

import com.iipsen2.app.daos.DAO;

public class UploadService {

    private DAO UploadDAO;

    public UploadService(DAO UploadDAO) {
        this.UploadDAO = UploadDAO;
    }
}
