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

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class CreateNewsPage extends EspressoBaseTest {
    private final int categoryInputId = com.google.android.material.R.id.text_input_end_icon;
    private final int dateField = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeField = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;
    private final int okButton = android.R.id.button1;


    public void chooseCategoryAndTitle(String title) {
        Allure.step("Выбор категории новостей с  названием: " + title);
        onView(allOf(withId(categoryInputId),
                withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(title))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
    }

    public void addNewsDate() {
        Allure.step("Выбор текущей даты для новости");
        onView(withId(dateField)).perform(click());
        onView(withId(okButton)).perform(click());
    }

    public void addNewsTime() {
        Allure.step("Выбор текущего времени для новости");
        onView(withId(timeField)).perform(scrollTo(), click());
        onView(withId(okButton)).perform(scrollTo(), click());
    }

    public void addNewsDescription(String description) {
        Allure.step("Добавление описания для новости с текстом: " + description);
        onView(withId(descriptionField))
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public void addNewsDateWithPaste(String date) {
        Allure.step("Выбор даты для новости вставкой значения: " + date);
        onView(withId(dateField))
                .perform(longClick()).perform(replaceText(date), closeSoftKeyboard());
    }

    public static int getSaveButtonId() {
        return R.id.save_button;
    }

}
