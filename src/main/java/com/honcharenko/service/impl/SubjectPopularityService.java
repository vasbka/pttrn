package com.honcharenko.service.impl;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.SubjectPopularity;
import com.honcharenko.service.BasicService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

public class SubjectPopularityService extends BasicService<SubjectPopularity> {
    public SubjectPopularityService() {
        super(SubjectPopularity.class);
        this.daoManager = new DaoManager(DaoType.NOSQL);
        this.factory = daoManager.getFactory();
    }
}
