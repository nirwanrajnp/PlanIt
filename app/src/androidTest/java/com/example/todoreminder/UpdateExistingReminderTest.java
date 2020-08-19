package com.example.todoreminder;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UpdateExistingReminderTest {

    @Rule
    public IntentsTestRule<HomeActivities> mActivityTestRule = new IntentsTestRule<>(HomeActivities.class);

    @Test
    public void updateExistingReminderTest() {

        ViewInteraction floatingActionButton = onView(
<<<<<<< HEAD
                allOf(withId(R.id.addItems),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
=======
                withId(R.id.addItems));
>>>>>>> e586d4bc35c19467c364fae66a44f8da9d8f9c03
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
               withId(R.id.addtext));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.addtext));
        appCompatEditText2.perform(replaceText("Math exam"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                withId(R.id.button1));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                withId(android.R.id.button1));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                withId(R.id.button2));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                withId(android.R.id.button1));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                withId(R.id.savebt));
        appCompatButton6.perform(click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(withId(R.id.homeItems));
        relativeLayout.perform(click());

        ViewInteraction appCompatButton7 = onView(
                withId(R.id.editbt));
        appCompatButton7.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.addtext));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.addtext));
        appCompatEditText4.perform(replaceText("English exam"));

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.addtext));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                withId(R.id.addtext));
        appCompatEditText6.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.addtext));
        appCompatEditText7.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                withId(R.id.addtext));
        appCompatEditText8.perform(click());

        ViewInteraction appCompatButton8 = onView(
                withId(R.id.button1));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                withId(android.R.id.button1));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatButton10 = onView(
                withId(R.id.button2));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                withId(android.R.id.button1));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatButton12 = onView(
                withId(R.id.savebt));
        appCompatButton12.perform(click());

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(withId(R.id.homeItems));
        relativeLayout2.perform(click());

        ViewInteraction textView = onView(
                withId(R.id.reminderdtv));
        textView.check(matches(withText("English exam")));




    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
