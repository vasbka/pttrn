package com.honcharenko.observer.impl;

import com.honcharenko.observer.Publisher;
import com.honcharenko.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class DaoPublisher implements Publisher {
    private Set<Subscriber> subscribers = new HashSet<>();

    @Override
    public void notifySubscribers(String message) {
        subscribers.forEach(subscriber -> subscriber.update(message));
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
