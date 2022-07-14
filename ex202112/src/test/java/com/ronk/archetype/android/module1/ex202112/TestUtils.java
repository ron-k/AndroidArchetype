package com.ronk.archetype.android.module1.ex202112;

public class TestUtils {
    private TestUtils() {
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}
