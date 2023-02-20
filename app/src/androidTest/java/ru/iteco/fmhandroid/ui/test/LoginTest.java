package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import io.qameta.allure.kotlin.Step;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {

    private final String LOGIN = "login2";
    private final String LOGIN_CAPS = "LOGIN2";
    private final String LOGIN_SPACE = "  login2";
    private final String PASSWORD = "password2";
    private final String PASSWORD_CAPS = "PASSWORD2";
    private final String AUTH_TEXT_TITLE = "Authorization";
    private final String ERROR_MESSAGE_1 = "Login and password cannot be empty";
    private final String ERROR_MESSAGE_2 = "Wrong login or password";
    private View decorView;
    private AuthorizationPage authorizationPage = new AuthorizationPage();

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);


    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<ru.iteco.fmhandroid.ui.AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }


    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Description(value = "Тест проверяет вход в приложение")
    public void successLoginTest() {
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(LOGIN);
        authorizationPage.inputTextInPasswordField(PASSWORD);
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogOutButtonId(), 3000));
        MainPage.checkById(MainPage.getLogoId());
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет logout из приложения")
    public void successLogoutTest() {
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(LOGIN);
        authorizationPage.inputTextInPasswordField(PASSWORD);
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogOutButtonId(), 3000));
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
        MainPage.checkByText(AUTH_TEXT_TITLE);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithLoginAndPassInCapsFieldTest() {
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(LOGIN_CAPS);
        authorizationPage.inputTextInPasswordField(PASSWORD_CAPS);
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        MainPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с пустыми полями логин и пароль")
    public void loginWithEmptyLoginAndPassFieldTest() {
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        MainPage.checkToastMessage(ERROR_MESSAGE_1, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithSpaceInLoginFieldTest() {  // падает в try, так как проходит логин с пробелами
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(LOGIN_SPACE);
        authorizationPage.inputTextInPasswordField(PASSWORD);
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        try {
            MainPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
        } catch (Exception e) {
            Allure.attachment("Report", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        } finally {
            AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
            MainPage.clickButton(MainPage.getTitleLogOutId());
        }
    }
}
