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
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.MissionPage;
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;


@RunWith(AllureAndroidJUnit4.class)
public class AppMissionActivityTest extends BaseTest {
    private static final String MISSION_TEXT = "Love is all";
    private static final String MISSION_ITEM_TEXT = "\"Хоспис для меня - это то, каким должен быть мир.\"";
    private final static MainPage mainPage = new MainPage();
    private final static AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MissionPage missionPage = new MissionPage();

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
        mainPage.clickButton(MainPage.getLogOutButtonId());
        mainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Description(value = "Тест проверяет работу страницы миссии и цитат")
    public void appMissionActivityTest() {
        mainPage.waitMainPage();
        mainPage.clickButton(MainPage.getMissionButtonId());
        mainPage.checkTextById(MainPage.getMissionTitleId(), MISSION_TEXT);
        missionPage.checkMissionItem(MISSION_ITEM_TEXT);
    }
}
