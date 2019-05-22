package com.honcharenko.service.impl;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.service.BasicService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;


public class EnrolleeService extends BasicService<Enrollee> {

    public EnrolleeService(DaoType daoType) {
        super(Enrollee.class);
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();
    }
}
