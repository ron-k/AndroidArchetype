package com.ronk.archetype.android;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FizzBuzz {
    @Test
    public void test()  {
        assertEquals("", fizzbuzz(1));
        assertEquals("", fizzbuzz(2));
        assertEquals("fizz", fizzbuzz(3));
        assertEquals("", fizzbuzz(4));
        assertEquals("buzz", fizzbuzz(5));
        assertEquals("fizz", fizzbuzz(6));
        assertEquals("", fizzbuzz(7));
        assertEquals("fizzbuzz", fizzbuzz(15));
    }

    private static String fizzbuzz(int n) {
        F2 f2 = (d, word) -> n % d == 0 ? (in -> word + in) : in -> in;

        F1 fizz = f2.apply(3, "fizz");
        F1 buzz = f2.apply(5, "buzz");

        return fizz.apply(buzz.apply(""));
    }

    interface F2 {
        F1 apply(int d, String appended);
    }

    interface F1 {

        String apply(String inStr);
    }
}