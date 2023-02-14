package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppActivityClaimTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setUp() {
        SystemClock.sleep(6000);
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
    }

    @After
    public void tearDown() {
        SystemClock.sleep(3000);
        onView(withId(R.id.authorization_image_button)).perform(click());
        onView(allOf(withId(android.R.id.title), withText("Log out"))).perform(click());
    }

    @Test
    public void createNewClaimTest() {
        String claimName = TestUtilities.getRandomClaimName();
        ViewInteraction materialButton1 = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
        materialButton1.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction materialButton2 = onView(withId(R.id.add_new_claim_material_button));
        materialButton2.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction textInputEditText4 = onView(withId(R.id.title_edit_text));
        textInputEditText4.perform(click());
        textInputEditText4.perform(replaceText(claimName), closeSoftKeyboard());
        ViewInteraction textInputEditText5 = onView(withId(R.id.date_in_plan_text_input_edit_text));
        textInputEditText5.perform(click());
        ViewInteraction materialButton3 = onView(allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText6 = onView(withId(R.id.time_in_plan_text_input_edit_text));
        textInputEditText6.perform(click());
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText7 = onView(withId(R.id.description_edit_text));
        textInputEditText7.perform(click());
        textInputEditText7.perform(replaceText(TestUtilities.getRandomComment()), closeSoftKeyboard());
        ViewInteraction materialButton5 = onView((withId(R.id.save_button)));
        materialButton5.perform(scrollTo(), click());
        SystemClock.sleep(3000);
        materialButton1.perform(click());
        ViewInteraction materialTextView = onView(withId(R.id.all_claims_text_view));
        materialTextView.perform(click());
        onView(ViewMatchers.withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(claimName)))).check(matches(isDisplayed()));
    }

    @Test
    public void createNewClaimWithWrongTimeTest() {
        ViewInteraction materialButton1 = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
        materialButton1.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction materialButton2 = onView(withId(R.id.add_new_claim_material_button));
        materialButton2.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction textInputEditText4 = onView(withId(R.id.title_edit_text));
        textInputEditText4.perform(click());
        String claim = TestUtilities.getRandomClaimName();
        textInputEditText4.perform(replaceText(claim), closeSoftKeyboard());
        ViewInteraction textInputEditText5 = onView(withId(R.id.date_in_plan_text_input_edit_text));
        textInputEditText5.perform(click());
        ViewInteraction materialButton3 = onView(allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText6 = onView(withId(R.id.time_in_plan_text_input_edit_text));
        textInputEditText6.perform(click());
        ViewInteraction appCompatImageButton = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input.")));
        appCompatImageButton.perform(click());
        ViewInteraction appCompatEditText = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), childAtPosition(allOf(withClassName(is("android.widget.RelativeLayout")), childAtPosition(withClassName(is("android.widget.TextInputTimePickerView")), 1)), 0),
                isDisplayed()));
        appCompatEditText.perform(replaceText("25"), closeSoftKeyboard());
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textView = onView(allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Enter a valid time")));
        textView.check(matches(withText("Enter a valid time")));
        onView(allOf(withId(android.R.id.button2), withText("Cancel"))).perform(click());
        SystemClock.sleep(3000);
        onView(allOf(withId(R.id.cancel_button), withText("Cancel"))).perform(scrollTo(), click());
        materialButton3.perform(click());
    }

    @Test
    public void createNewClaimWithWrongDateTest() {  //должен падать с сообщением о не корректной дате
        ViewInteraction materialButton1 = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
        materialButton1.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction materialButton2 = onView(withId(R.id.add_new_claim_material_button));
        materialButton2.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction textInputEditText4 = onView(withId(R.id.title_edit_text));
        textInputEditText4.perform(click());
        textInputEditText4.perform(replaceText(TestUtilities.getRandomClaimName()), closeSoftKeyboard());
        ViewInteraction textInputEditText5 = onView(withId(R.id.date_in_plan_text_input_edit_text));
        textInputEditText5.perform(longClick());
        textInputEditText5.perform(replaceText("01.01.0001"), closeSoftKeyboard());
        ViewInteraction textInputEditText6 = onView(withId(R.id.time_in_plan_text_input_edit_text));
        textInputEditText6.perform(click());
        ViewInteraction materialButton3 = onView(allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());
        ViewInteraction textInputEditText7 = onView(withId(R.id.description_edit_text));
        textInputEditText7.perform(replaceText(TestUtilities.getRandomComment()), closeSoftKeyboard());
        ViewInteraction materialButton5 = onView((withId(R.id.save_button)));
        materialButton5.perform(scrollTo(), click());
    }

    @Test
    public void createNewClaimWithEmptyFieldsTest() {
        ViewInteraction materialButton1 = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
        materialButton1.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction materialButton2 = onView(withId(R.id.add_new_claim_material_button));
        materialButton2.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction materialButton5 = onView((withId(R.id.save_button)));
        materialButton5.perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.message), withText("Fill empty fields"))).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        onView(allOf(withId(R.id.cancel_button), withText("Cancel"))).perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
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
