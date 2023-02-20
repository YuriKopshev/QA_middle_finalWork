package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class EspressoBaseTest {

    @Step(value = "Клик по кнопке c id")
    public static void clickButton(Integer resourceId) {
        onView((withId(resourceId))).perform(click());
    }

    @Step(value = "Клик по кнопке c id со скроллом")
    public static void clickButtonWithScroll(Integer resourceId) {
        onView((withId(resourceId))).perform(scrollTo(), click());
    }

    @Step(value = "Проверка отображения элемента на странице c id: {resourceId}")
    public static void checkById(Integer resourceId) {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    @Step(value = "Проверка отображения текста на странице")
    public static void checkByText(String text) {
        onView((withText(text))).check(matches(withText(text)));
    }

    @Step(value = "Проверка отображения текста на странице по id")
    public static void checkTextById(Integer resourceId, String text) {
        onView((withId(resourceId))).check(matches(withText(text)));
    }

    @Step("Проверка отображения сообщения об ошибке")
    public static void checkToastMessage(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }


}
