package com.ronk.ex20220714;


import static com.ronk.archetype.android.TestUtils.assertCurrentActivity;
import static com.ronk.archetype.android.TestUtils.launchApp;
import static com.ronk.archetype.android.TestUtils.waitActivityTransition;

import com.ronk.ex20220714.flickr_gallery.FlickrGalleryActivity;

import org.junit.Test;


public class SmokeTest {


    @Test
    public void sanityRun() throws Exception {
        launchApp();

        waitActivityTransition(); // Search Activity

        assertCurrentActivity(FlickrGalleryActivity.class);

    /*
        onView(withId(R.id.add_widget_btn_add_widget))
                .check(matches(
                        ViewMatchers.withEffectiveVisibility(scenario.isWidgetAlreadyAdded() ? ViewMatchers.Visibility.GONE : ViewMatchers.Visibility.VISIBLE)));

        testLanguageSelection();

        ViewInteraction searchEdit = onView(withId(R.id.edt_search));
        searchEdit.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
        searchEdit.perform(ViewActions.typeTextIntoFocusedView(scenario.getSearchTerm()));

        Thread.sleep(3000); //wait for suggestions
        searchEdit.check(ViewAssertions.matches(new BoundedMatcher<View, AutoCompleteTextView>(AutoCompleteTextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("must have autosuggestion popup showing");
            }

            @Override
            protected boolean matchesSafely(AutoCompleteTextView item) {
                return item.isPopupShowing();
            }
        }));


        waitActivityTransition(); // browser app

         */
    }


}
