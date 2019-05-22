package com.honcharenko.service.impl;

import com.honcharenko.entity.Point;
import com.honcharenko.service.BasicService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

public class PointService extends BasicService<Point> {
    public PointService(DaoType daoType) {
        super(Point.class);
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();
    }
}
