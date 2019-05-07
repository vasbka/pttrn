package com.honcharenko.service.impl;

import com.honcharenko.entity.Faculty;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

public class FacultyService extends BasicServicce<Faculty> {

    public FacultyService(DaoType daoType) {
        super(Faculty.class);
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();

    }
}
