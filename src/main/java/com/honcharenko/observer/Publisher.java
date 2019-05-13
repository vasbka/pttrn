package com.honcharenko.observer;

public interface Publisher {

    void notifySubscribers(String message);

    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);
}
