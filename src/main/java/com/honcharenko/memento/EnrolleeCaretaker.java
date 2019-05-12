package com.honcharenko.memento;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.memento.impl.EnrolleeSnapshot;

import java.util.LinkedList;

public class EnrolleeCaretaker {
    private LinkedList<EnrolleeSnapshot> snapshots;
    private static EnrolleeCaretaker instance;


    private EnrolleeCaretaker() {
        snapshots = new LinkedList<>();
    }

    public static EnrolleeCaretaker getInstance() {
        if (instance == null) {
            instance = new EnrolleeCaretaker();
        }
        return instance;
    }

    public void addSnapshot(EnrolleeSnapshot enrolleeSnapshot) {
        snapshots.push(enrolleeSnapshot);
    }

    public Enrollee restore() {
        EnrolleeSnapshot enrolleeSnapshot;
        if ((enrolleeSnapshot = snapshots.poll()) != null) {
            return enrolleeSnapshot.restore();
        }
        return null;
    }

}
