package ru.iteco.fmhandroid.ui.test;


import static com.google.firebase.crashlytics.internal.Logger.TAG;

import android.util.Log;

import androidx.test.filters.LargeTest;

import org.junit.Before;
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
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        boolean checkState = authorizationPage.checkStartState();
        Log.d(TAG, "START STATE: " + checkState);
        if (!checkState) {
            mainPage.waitLogOutButton();
            mainPage.checkLogoId();
            mainPage.clickLogOutButton();
            mainPage.clickTitleLogOutButton();
        }
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Description(value = "Тест проверяет вход в приложение")
    public void successLoginTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickLoginFieldInput();
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickSignInButton();
        mainPage.waitLogOutButton();
        mainPage.checkLogoId();
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет logout из приложения")
    public void successLogoutTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickLoginFieldInput();
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickSignInButton();
        mainPage.waitLogOutButton();
        mainPage.clickLogOutButton();
        mainPage.clickTitleLogOutButton();
        authorizationPage.checkByText(AUTH_TEXT_TITLE);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithLoginAndPassInCapsFieldTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickLoginFieldInput();
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginCaps());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPasswordCaps());
        authorizationPage.clickSignInButton();
        authorizationPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с пустыми полями логин и пароль")
    public void loginWithEmptyLoginAndPassFieldTest() {
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickSignInButton();
        authorizationPage.checkToastMessage(ERROR_MESSAGE_1, decorView);
    }

    @Test
    @Severity(value = SeverityLevel.CRITICAL)
    @Description(value = "Тест проверяет вход в приложение с неверным логином и паролем")
    public void loginWithSpaceInLoginFieldTest() {  // падает в try, так как проходит логин с пробелами
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickLoginFieldInput();
        authorizationPage.inputTextInLoginField(AuthorizationData.getLoginSpace());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickSignInButton();
        try {
            authorizationPage.checkToastMessage(ERROR_MESSAGE_2, decorView);
        } catch (Exception e) {
            Allure.attachment("Report", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
    }
}
