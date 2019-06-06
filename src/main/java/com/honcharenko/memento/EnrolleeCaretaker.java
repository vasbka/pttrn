package com.honcharenko.memento;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.memento.impl.EnrolleeSnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class EnrolleeCaretaker {
    private ArrayList<EnrolleeSnapshot> snapshots;
    private static EnrolleeCaretaker instance;
    private int currentIndex = -1;
    private EnrolleeSnapshot current;

    private EnrolleeCaretaker() {
        snapshots = new ArrayList<>();
    }

    public static EnrolleeCaretaker getInstance() {
        if (instance == null) {
            instance = new EnrolleeCaretaker();
        }
        return instance;
    }

    public void addSnapshot(EnrolleeSnapshot enrolleeSnapshot) {
        snapshots.add(enrolleeSnapshot);
        currentIndex++;
    }

    public EnrolleeSnapshot getCurrentSnap() {
        if (!snapshots.isEmpty()) {
            current = snapshots.get(currentIndex);
        }
        return current;
    }

    public EnrolleeSnapshot moveBack() {
        if (currentIndex == -1) {
            return null;
        }
        current = snapshots.get(--currentIndex);
        return current;
    }

    public EnrolleeSnapshot moveForward() {
        if (snapshots.size() > currentIndex + 1) {
            current = snapshots.get(++currentIndex);
            return current;
        }
        return null;
    }

    public Enrollee restore() {
        EnrolleeSnapshot snapshotToRestore = null;
        if (!snapshots.isEmpty()) {
            snapshotToRestore = snapshots.get(currentIndex);
        }
        if (snapshotToRestore == null) {
            System.out.println("Current snapshot is null");
            return null;
        }
        return snapshotToRestore.restore();
    }

}
