package com.honcharenko.service.impl;

import com.honcharenko.entity.Subject;
import com.honcharenko.service.BasicService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

public class SubjectService extends BasicService<Subject> {
    public SubjectService(DaoType daoType) {
        super(Subject.class);
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();
    }
}
