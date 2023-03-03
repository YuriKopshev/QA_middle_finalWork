package ru.iteco.fmhandroid.ui.test;


import androidx.test.core.app.ActivityScenario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.pages.AboutAppPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;


@RunWith(AllureAndroidJUnit4.class)
public class AppAboutActivityTest extends BaseTest {
    private final static String ITEM_ABOUT = "About";
    private static final String APP_VERSION = "1.0.0";
    private static final String APP_DEVELOPER = "© I-Teco, 2022";
    private final static AuthorizationPage authorizationPage = new AuthorizationPage();
    private final static MainPage mainPage = new MainPage();
    private final AboutAppPage aboutAppPage = new AboutAppPage();

    @BeforeClass
    public static void setUp() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
        authorizationPage.waitAuthorizationPage();
        authorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        authorizationPage.clickButton(AuthorizationPage.getSignInButton());
    }

    @AfterClass
    public static void tearDown() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
        mainPage.waitLogOutButton();
        authorizationPage.clickButton(MainPage.getLogOutButtonId());
        mainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет отображение информации о версии и разработчике приложения")
    public void appAboutActivityTest() {
        mainPage.waitMainPage();
        mainPage.chooseMainMenuItem(ITEM_ABOUT);
        aboutAppPage.checkAppVersion(APP_VERSION);
        aboutAppPage.checkAppDeveloper(APP_DEVELOPER);
        authorizationPage.clickButton(AboutAppPage.getBackIdButton());
    }
}
