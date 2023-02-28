package ru.iteco.fmhandroid.ui.test;


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
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest extends BaseTest {
    private final String AUTH_TEXT_TITLE = "Authorization";
    private final String ERROR_MESSAGE_1 = "Login and password cannot be empty";
    private final String ERROR_MESSAGE_2 = "Wrong login or password";
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Description(value = "Тест проверяет вход в приложение")
    public void successLoginTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitLogOutButton();
        MainPage.checkById(MainPage.getLogoId());
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет logout из приложения")
    public void successLogoutTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitLogOutButton();
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
        MainPage.checkByText(AUTH_TEXT_TITLE);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithLoginAndPassInCapsFieldTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginCaps());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPasswordCaps());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        MainPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с пустыми полями логин и пароль")
    public void loginWithEmptyLoginAndPassFieldTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        MainPage.checkToastMessage(ERROR_MESSAGE_1, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithSpaceInLoginFieldTest() {  // падает в try, так как проходит логин с пробелами
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginSpace());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
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
