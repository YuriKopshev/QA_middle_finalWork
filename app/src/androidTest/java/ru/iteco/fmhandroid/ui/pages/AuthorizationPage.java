package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

import io.qameta.allure.kotlin.Allure;
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

    public void waitAuthorizationPage(){
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
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
}


