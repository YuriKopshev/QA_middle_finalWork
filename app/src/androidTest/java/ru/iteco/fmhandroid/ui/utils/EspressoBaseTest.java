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

import io.qameta.allure.kotlin.Allure;


public class EspressoBaseTest {

    public void clickButton(Integer resourceId) {
        Allure.step("Клик по кнопке c id: " + resourceId);
        onView((withId(resourceId))).perform(click());
    }

    public void clickButtonWithScroll(Integer resourceId) {
        Allure.step("Клик со скроллом по кнопке c id: " + resourceId);
        onView((withId(resourceId))).perform(scrollTo(), click());
    }

    public void checkById(Integer resourceId) {
        Allure.step("Проверка отображения элемента на странице c id: " + resourceId);
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public void checkByText(String text) {
        Allure.step("Проверка отображения текста: " + text + "на странице");
        onView((withText(text))).check(matches(withText(text)));
    }

    public void checkTextById(Integer resourceId, String text) {
        Allure.step("Проверка отображения текста: " + text + " на странице по id: " + resourceId);
        onView((withId(resourceId))).check(matches(withText(text)));
    }

    public void checkToastMessage(String text, View decorView) {
        Allure.step("Проверка отображения сообщения об ошибке c текстом: " + text);
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

}
