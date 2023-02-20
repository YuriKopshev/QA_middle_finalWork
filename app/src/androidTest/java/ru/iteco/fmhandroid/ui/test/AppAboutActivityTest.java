package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AboutAppPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;


@RunWith(AllureAndroidJUnit4.class)
public class AppAboutActivityTest {
    private static final String LOGIN = "login2";
    private static final String PASSWORD = "password2";
    private final static String ITEM_ABOUT = "About";
    private static final String APP_VERSION = "1.0.0";
    private static final String APP_DEVELOPER = "© I-Teco, 2022";
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final AboutAppPage aboutAppPage = new AboutAppPage();


    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitDisplayed(AuthorizationPage.getLoginFieldInput(), 5000));
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(LOGIN);
        authorizationPage.inputTextInPasswordField(PASSWORD);
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
    }

    @After
    public void tearDown() {
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogOutButtonId(), 3000));
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет отображение информации о версии и разработчике приложения")
    public void appAboutActivityTest() {
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogoId(), 5000));
        mainPage.chooseMainMenuItem(ITEM_ABOUT);
        aboutAppPage.checkAppVersion(APP_VERSION);
        aboutAppPage.checkAppDeveloper(APP_DEVELOPER);
        AboutAppPage.clickButton(AboutAppPage.getBackIdButton());
    }

}
