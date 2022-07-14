package com.ronk.archetype.android.module1.ex202112;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

public class Question {

    private static final String TAG = Question.class.getSimpleName();

    public void ask() {
        List<Integer> inList = chooseListCreator().createInList();
        int targetSum = 42;

        Solution sol = chooseSolution();
        if (sol.containsSum(inList, targetSum) != null) {
            Log.d(TAG, "contains sum");
        } else {
            Log.d(TAG, "doesn't contain sum");
        }
    }

    @NonNull
    private ListCreator chooseListCreator() {
        return new ListCreatorLinear(-15, 50);
    }

    @NonNull
    private Solution chooseSolution() {
        return new Solution1Naive();
    }
}
