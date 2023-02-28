package ru.iteco.fmhandroid.ui.test;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.MissionPage;
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;


@RunWith(AllureAndroidJUnit4.class)
public class AppMissionActivityTest extends BaseTest {
    private static final String MISSION_TEXT = "Love is all";
    private static final String MISSION_ITEM_TEXT = "\"Хоспис для меня - это то, каким должен быть мир.\"";
    private final static MainPage mainPage = new MainPage();
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MissionPage missionPage = new MissionPage();

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Description(value = "Тест проверяет работу страницы миссии и цитат")
    public void appMissionActivityTest() {
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
        mainPage.waitMainPage();
        MainPage.clickButton(MainPage.getMissionButtonId());
        MainPage.checkTextById(MainPage.getMissionTitleId(), MISSION_TEXT);
        missionPage.checkMissionItem(MISSION_ITEM_TEXT);
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }
}
