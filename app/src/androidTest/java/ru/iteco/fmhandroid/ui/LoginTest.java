package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<ru.iteco.fmhandroid.ui.AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void a_successLoginTest() {
        SystemClock.sleep(5000);
        ViewInteraction textInputEditText = onView((withId(R.id.login_text_input_layout)));
        textInputEditText.perform(click());
        ViewInteraction textInputEditText2 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.login_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText2.perform(replaceText("login2"), closeSoftKeyboard());
        textInputEditText2.perform(pressImeActionButton());
        ViewInteraction textInputEditText3 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.password_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText3.perform(replaceText("password2"), closeSoftKeyboard());
        textInputEditText3.perform(pressImeActionButton());
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction imageView = onView(withId(R.id.trademark_image_view));
        imageView.check(matches(isDisplayed()));
        ViewInteraction materialButton2 = onView(withId(R.id.authorization_image_button));
        materialButton2.perform(click());
        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText("Log out")));
        materialTextView.perform(click());
    }

    @Test
    public void b_successLogoutTest() {
        SystemClock.sleep(5000);
        ViewInteraction textInputEditText = onView((withId(R.id.login_text_input_layout)));
        textInputEditText.perform(click());
        ViewInteraction textInputEditText2 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.login_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText2.perform(replaceText("login2"), closeSoftKeyboard());
        textInputEditText2.perform(pressImeActionButton());
        ViewInteraction textInputEditText3 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.password_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText3.perform(replaceText("password2"), closeSoftKeyboard());
        textInputEditText3.perform(pressImeActionButton());
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.perform(click());
        SystemClock.sleep(3000);
        ViewInteraction materialButton2 = onView(withId(R.id.authorization_image_button));
        materialButton2.perform(click());
        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText("Log out")));
        materialTextView.perform(click());
        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
    }

    @Test
    public void c_LoginWithLoginAndPassInCapsFieldTest() {
        SystemClock.sleep(5000);
        ViewInteraction textInputEditText = onView((withId(R.id.login_text_input_layout)));
        textInputEditText.perform(click());
        ViewInteraction textInputEditText2 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.login_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText2.perform(replaceText("LOGIN2"), closeSoftKeyboard());
        textInputEditText2.perform(pressImeActionButton());
        ViewInteraction textInputEditText3 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.password_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText3.perform(replaceText("PASSWORD2"), closeSoftKeyboard());
        textInputEditText3.perform(pressImeActionButton());
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.perform(click());
        onView(withText("Wrong login or password"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void d_LoginWithEmptyLoginAndPassFieldTest() {
        SystemClock.sleep(6000);
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.perform(click());
        onView(withText("Login and password cannot be empty"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void e_loginWithSpaceInLoginFieldTest() {  // падает так как проходит логин с пробелами
        SystemClock.sleep(5000);
        ViewInteraction textInputEditText = onView((withId(R.id.login_text_input_layout)));
        textInputEditText.perform(click());
        ViewInteraction textInputEditText2 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.login_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText2.perform(replaceText("  login2"), closeSoftKeyboard());
        textInputEditText2.perform(pressImeActionButton());
        ViewInteraction textInputEditText3 = onView(allOf(childAtPosition(childAtPosition(withId(R.id.password_text_input_layout), 0), 0), isDisplayed()));
        textInputEditText3.perform(replaceText("password2"), closeSoftKeyboard());
        textInputEditText3.perform(pressImeActionButton());
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.perform(click());
        SystemClock.sleep(4000);
        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
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
