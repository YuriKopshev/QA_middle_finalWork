package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class CreateNewsPage extends EspressoBaseTest {
    private final int categoryInputId = com.google.android.material.R.id.text_input_end_icon;
    private final int dateField = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeField = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;
    private final int okButton = android.R.id.button1;


    @Step(value = "Choose news category")
    public void chooseCategoryAndTitle(String title) {
        onView(allOf(withId(categoryInputId),
                withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(title))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
    }

    @Step(value = "Choose news date")
    public void addNewsDate() {
        onView(withId(dateField)).perform(click());
        onView(withId(okButton)).perform(click());
    }

    @Step(value = "Choose news time")
    public void addNewsTime() {
        onView(withId(timeField)).perform(scrollTo(), click());
        onView(withId(okButton)).perform(scrollTo(), click());
    }

    @Step(value = "Add news description")
    public void addNewsDescription(String description) {
        onView(withId(descriptionField))
                .perform(replaceText(description), closeSoftKeyboard());
    }

    @Step(value = "Add news date with paste")
    public void addNewsDateWithPaste(String date) {
        onView(withId(dateField))
                .perform(longClick()).perform(replaceText(date), closeSoftKeyboard());
    }

    public static int getSaveButtonId() {
        return R.id.save_button;
    }

}
