package ru.iteco.fmhandroid.ui.test;


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
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final AboutAppPage aboutAppPage = new AboutAppPage();

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет отображение информации о версии и разработчике приложения")
    public void appAboutActivityTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitMainPage();
        mainPage.chooseMainMenuItem(ITEM_ABOUT);
        aboutAppPage.checkAppVersion(APP_VERSION);
        aboutAppPage.checkAppDeveloper(APP_DEVELOPER);
        AboutAppPage.clickButton(AboutAppPage.getBackIdButton());
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }
}
