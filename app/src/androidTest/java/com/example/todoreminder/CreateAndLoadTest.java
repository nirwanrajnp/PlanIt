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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAndLoadTest {

    @Rule
    public IntentsTestRule<HomeActivities> mActivityTestRule = new IntentsTestRule<>(HomeActivities.class);
    
    //test the create and load reminder funtion
    @Test
    public void createAndLoadTest() {
        
        //click add button goes to addnewItem page
        ViewInteraction floatingActionButton = onView(
               withId(R.id.addItems));
        floatingActionButton.perform(click());
    
        //input text in the input detail box
        ViewInteraction appCompatEditText = onView(
                withId(R.id.addtext));
        appCompatEditText.perform(replaceText("aaa"), closeSoftKeyboard());
        
        //press the button to select date
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

        ViewInteraction textView = onView(
               withId(R.id.text1));
        textView.check(matches(withText("aaa")));

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(withId(R.id.homeItems));//delete later
        relativeLayout.perform(click());

        /*DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.homeItems),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(0);
        relativeLayout.perform(click());*/

        ViewInteraction textView2 = onView(
                withId(R.id.reminderdtv));
        textView2.check(matches(withText("aaa")));

        ViewInteraction textView3 = onView(
                withId(R.id.datetv));
        textView3.check(matches(withText("18/10/2019")));


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
