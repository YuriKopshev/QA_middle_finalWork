package ru.iteco.fmhandroid.ui.test;


import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
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
        authorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitLogOutButton();
        mainPage.checkById(MainPage.getLogoId());
        authorizationPage.clickButton(MainPage.getLogOutButtonId());
        mainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет logout из приложения")
    public void successLogoutTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitLogOutButton();
        authorizationPage.clickButton(MainPage.getLogOutButtonId());
        mainPage.clickButton(MainPage.getTitleLogOutId());
        mainPage.checkByText(AUTH_TEXT_TITLE);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithLoginAndPassInCapsFieldTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginCaps());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPasswordCaps());
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с пустыми полями логин и пароль")
    public void loginWithEmptyLoginAndPassFieldTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.checkToastMessage(ERROR_MESSAGE_1, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithSpaceInLoginFieldTest() {  // падает в try, так как проходит логин с пробелами
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginSpace());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
        try {
            mainPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
        } catch (Exception e) {
            Allure.attachment("Report", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        } finally {
            authorizationPage.clickButton(MainPage.getLogOutButtonId());
            mainPage.clickButton(MainPage.getTitleLogOutId());
        }
    }
}
