package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
//import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;


import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class AuthorizationPage extends EspressoBaseTest {
    private final int loginFieldId = R.id.login_text_input_layout;
    private final int passwordFieldId = R.id.password_text_input_layout;

    public static int getLoginFieldInput() {
        return R.id.login_text_input_layout;
    }

    public static int getSignInButton() {
        return R.id.enter_button;
    }

    @Step("Вводим текст в поле логин")
    public void inputTextInLoginField(String text) {
        onView(allOf(childAtPosition(childAtPosition(withId(loginFieldId), 0), 0), isDisplayed()))
                .perform(replaceText(text), closeSoftKeyboard())
                .perform(pressImeActionButton());
    }

    @Step("Вводим текст в поле пароль")
    public void inputTextInPasswordField(String text) {
        onView(allOf(childAtPosition(childAtPosition(withId(passwordFieldId), 0), 0), isDisplayed()))
                .perform(replaceText(text), closeSoftKeyboard())
                .perform(pressImeActionButton());
    }
}


