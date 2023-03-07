package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    private final int newsList = R.id.news_list_recycler_view;
    private final int editButtonId = R.id.edit_news_material_button;
    private final int addNewsButtonId = R.id.add_news_image_view;

    public void checkAddedNews(String description, int position) {
        Allure.step("Проверка существования добавленной новости c названием: " + description);
        onView(withId(newsList)).perform(actionOnItemAtPosition(position, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText(description))).check(matches(isDisplayed()));
    }

    public void clickEditButton() {
        Allure.step("Клик по кнопке c id: " + editButtonId);
        onView((withId(editButtonId))).perform(click());
    }

    public void clickAddNewsButton() {
        Allure.step("Клик по кнопке c id: " + addNewsButtonId);
        onView((withId(addNewsButtonId))).perform(click());
    }
}
