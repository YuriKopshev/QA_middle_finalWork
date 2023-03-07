package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CreateNewsPage {
    private final int categoryInputId = com.google.android.material.R.id.text_input_end_icon;
    private final int dateField = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeField = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;
    private final int saveButtonId = R.id.save_button;
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

    public void clickButtonWithScroll(Integer resourceId) {
        Allure.step("Клик со скроллом по кнопке c id: " + resourceId);
        onView((withId(resourceId))).perform(scrollTo(), click());
    }

    public void checkToastMessage(String text, View decorView) {
        Allure.step("Проверка отображения сообщения об ошибке c текстом: " + text);
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void clickSaveButtonWithScroll() {
        Allure.step("Клик со скроллом по кнопке c id: " + saveButtonId);
        onView((withId(saveButtonId))).perform(scrollTo(), click());
    }
}
