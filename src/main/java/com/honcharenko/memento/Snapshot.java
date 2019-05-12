package com.honcharenko.memento;

import java.util.List;

public interface Snapshot<E> {
    E restore();
}
