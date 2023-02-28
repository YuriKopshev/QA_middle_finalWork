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
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class NewsPage extends EspressoBaseTest {
    private final int newsList = R.id.news_list_recycler_view;

    public static int getEditButtonId() {
        return R.id.edit_news_material_button;
    }

    public static int getAddNewsButtonId() {
        return R.id.add_news_image_view;
    }

    public void checkAddedNews(String description, int position) {
        Allure.step("Проверка существования добавленной новости c названием: " + description);
        onView(withId(newsList)).perform(actionOnItemAtPosition(position, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), withText(description))).check(matches(isDisplayed()));
    }
}
