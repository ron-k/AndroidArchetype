package com.ronk.archetype.android.module1.ex202112;

import java.util.Collections;
import java.util.List;

public enum ListCreatorEmpty implements ListCreator {
    INSTANCE;

    @Override
    public List<Integer> createInList() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
