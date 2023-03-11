package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

import android.view.View;

import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingViewException;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class AuthorizationPage {
    private final int loginFieldId = R.id.login_text_input_layout;
    private final int passwordFieldId = R.id.password_text_input_layout;
    private final int signInButtonId = R.id.enter_button;

    public void waitAuthorizationPage() {
        onView(isRoot()).perform(waitDisplayed(loginFieldId, 5000));
    }

    public void inputTextInLoginField(String text) {
        Allure.step("Ввод в поле логин текста: " + text);
        onView(allOf(childAtPosition(childAtPosition(withId(loginFieldId), 0), 0), isDisplayed()))
                .perform(replaceText(text), closeSoftKeyboard())
                .perform(pressImeActionButton());
    }

    public void inputTextInPasswordField(String text) {
        Allure.step("Ввод в поле пароль текста: " + text);
        onView(allOf(childAtPosition(childAtPosition(withId(passwordFieldId), 0), 0), isDisplayed()))
                .perform(replaceText(text), closeSoftKeyboard())
                .perform(pressImeActionButton());
    }

    public void checkToastMessage(String text, View decorView) {
        Allure.step("Проверка отображения сообщения об ошибке c текстом: " + text);
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void clickLoginFieldInput() {
        Allure.step("Клик по кнопке c id: " + loginFieldId);
        onView((withId(loginFieldId))).perform(click());
    }

    public void clickSignInButton() {
        Allure.step("Клик по кнопке c id: " + signInButtonId);
        onView((withId(signInButtonId))).perform(click());
    }

    public boolean checkStartState() {
        waitAuthorizationPage();
        return AuthorizationPage.viewIsDisplayed(loginFieldId);
    }

    public void checkByText(String text) {
        Allure.step("Проверка отображения текста: " + text + "на странице");
        onView((withText(text))).check(matches(withText(text)));
    }

    public static boolean viewIsDisplayed(int viewId) {
        final boolean[] isDisplayed = {true};
        onView(withId(viewId)).withFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable error, Matcher<View> viewMatcher) {
                isDisplayed[0] = false;
            }
        }).check(matches(isDisplayed()));
        return isDisplayed[0];
    }


    public static boolean isViewDisplayed(int viewId) {
        try {
            onView(withId(viewId)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException | AssertionFailedError e) {
            return false;
        }
    }
}



