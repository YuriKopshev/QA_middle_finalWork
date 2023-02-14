package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppAboutActivityTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        SystemClock.sleep(6000);
        onView((withId(R.id.login_text_input_layout))).perform(click());
        ViewInteraction textInputEditText2 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.login_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText2.perform(replaceText("login2"), closeSoftKeyboard());
        textInputEditText2.perform(pressImeActionButton());
        ViewInteraction textInputEditText3 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.password_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText3.perform(replaceText("password2"), closeSoftKeyboard());
        textInputEditText3.perform(pressImeActionButton());
        onView(withId(R.id.enter_button)).perform(click());
    }

    @After
    public void tearDown() {
        SystemClock.sleep(3000);
        onView(withId(R.id.authorization_image_button)).perform(click());
        onView(allOf(withId(android.R.id.title), withText("Log out"))).perform(click());
    }

    @Test
    public void appAboutActivityTest() {
        SystemClock.sleep(3000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("About"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.about_version_value_text_view), withText("1.0.0")));
        textView.check(matches(withText("1.0.0")));
        onView(allOf(withId(R.id.about_company_info_label_text_view), withText("© I-Teco, 2022"))).check(matches(isDisplayed()));
        onView(withId(R.id.about_back_image_button)).perform(click());
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
