package com.ronk.archetype.android;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;
import java.util.UUID;

public class TestUtils {

    public static void clickOn(int id) {
        ViewInteraction clickable = Espresso.onView(Matchers.allOf(
                ViewMatchers.withId(id),
                ViewMatchers.isEnabled(),
                ViewMatchers.isClickable(),
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        clickable.perform(click());
    }

    public static void waitActivityTransition() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Nullable
    public static Activity getCurrentActivity() {
        Activity[] currentActivity = {null};
        getInstrumentation().runOnMainSync(() -> {
            Collection<Activity> resumedActivities =
                    ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
            for (Activity activity : resumedActivities) {
                currentActivity[0] = activity;
                break;
            }
        });

        return currentActivity[0];
    }

    public static void launchApp() {
        Context context = getInstrumentation().getTargetContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        assert intent != null;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

    @NonNull
    public static <T, S extends T> Matcher<T> isInstanceOf(final Class<S> cl) {
        return new BoundedMatcher<>(cl) {
            @Override
            public void describeTo(Description description) {
                description.appendText("instance of ").appendText(cl.getName());
            }

            @Override
            protected boolean matchesSafely(Object item) {
                return true;
            }
        };
    }

    public static void assertCurrentActivity(Class<? extends Activity> activityClass) {
        Activity currentActivity = getCurrentActivity();
        assertThat(currentActivity, isInstanceOf(activityClass));
    }

    public static void assertViewGone(int id) {
        onView(withId(id)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    public static void assertViewVisible(int id) {
        onView(withId(id)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * @return a random text
     */
    @NonNull
    public static String aText() {
        return UUID.randomUUID().toString();
    }

}
