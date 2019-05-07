package com.honcharenko.observer.impl;

import com.honcharenko.observer.Subscriber;

public class DaoSubsciber implements Subscriber {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
