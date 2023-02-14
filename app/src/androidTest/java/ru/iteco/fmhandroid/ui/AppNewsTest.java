package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppNewsTest {

    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @BeforeClass
    public static void setUp() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
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

    @Before
    public void setDecorView() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<ru.iteco.fmhandroid.ui.AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @AfterClass
    public static void tearDown() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
        SystemClock.sleep(5000);
        onView(withId(R.id.authorization_image_button)).perform(click());
        onView(allOf(withId(android.R.id.title), withText("Log out"))).perform(click());
    }

    @Test
    public void a1_addNewsItemTest() {
        SystemClock.sleep(6000);
        String newsItemName = TestUtilities.getRandomNewsItem();
        onView(allOf(withId(R.id.all_news_text_view), withText("All news"))).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText("Объявление"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(newsItemName), closeSoftKeyboard());
        onView((withId(R.id.save_button))).perform(scrollTo(), click());
        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText(newsItemName))).check(matches(isDisplayed()));
    }

    @Test
    public void a2_addNewsItemTestFromMainMenu() {
        String newsItemName = TestUtilities.getRandomNewsItem();
        SystemClock.sleep(5000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText("Объявление"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(newsItemName), closeSoftKeyboard());
        onView((withId(R.id.save_button))).perform(scrollTo(), click());
        SystemClock.sleep(3000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Main"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        onView(allOf(withId(R.id.all_news_text_view), withText("All news"))).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText(newsItemName))).check(matches(isDisplayed()));
    }

    @Test
    public void a3_addNewsItemWithWrongDateTest() {   //должен падать так как нет сообщения о не корректной дате: "Wrong date!"
        SystemClock.sleep(6000);
        onView(allOf(withId(R.id.all_news_text_view), withText("All news"))).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText("Объявление"))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
        ViewInteraction textInputEditText1 = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
        textInputEditText1.perform(longClick());
        textInputEditText1.perform(replaceText("01.01.0001"), closeSoftKeyboard());
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(TestUtilities.getRandomNewsItem()), closeSoftKeyboard());
        onView((withId(R.id.save_button))).perform(scrollTo(), click());
        onView(withText("Wrong date!"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
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
