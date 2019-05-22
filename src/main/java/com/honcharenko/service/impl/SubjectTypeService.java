package com.honcharenko.service.impl;

import com.honcharenko.entity.SubjectType;
import com.honcharenko.service.BasicService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

public class SubjectTypeService extends BasicService<SubjectType> {
    public SubjectTypeService(DaoType daoType) {
        super(SubjectType.class);
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();
    }
}
