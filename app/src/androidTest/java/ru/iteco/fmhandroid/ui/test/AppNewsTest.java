package ru.iteco.fmhandroid.ui.test;


import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Flaky;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.CreateNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppNewsTest extends BaseTest {
    private final static String TITLE = "Объявление";
    private final static String ITEM_NEWS = "News";
    private final static String ITEM_MAIN = "Main";
    private final static String ERROR_MESSAGE_1 = "Wrong date!";
    private final static String WRONG_DATE = "01.01.0001";
    private final static AuthorizationPage authorizationPage = new AuthorizationPage();
    private final CreateNewsPage createNewsPage = new CreateNewsPage();
    private final NewsPage newsPage = new NewsPage();
    private final static MainPage mainPage = new MainPage();


    @BeforeClass
    public static void setUp() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
        authorizationPage.waitAuthorizationPage();
        AuthorizationPage.clickButton(AuthorizationPage.getLoginFieldInput());
        authorizationPage.inputTextInLoginField(AuthorizationData.getLogin());
        authorizationPage.inputTextInPasswordField(AuthorizationData.getPassword());
        AuthorizationPage.clickButton(AuthorizationPage.getSignInButton());
    }

    @AfterClass
    public static void tearDown() {
        ActivityScenario.launch(ru.iteco.fmhandroid.ui.AppActivity.class);
        mainPage.waitLogOutButton();
        AuthorizationPage.clickButton(MainPage.getLogOutButtonId());
        MainPage.clickButton(MainPage.getTitleLogOutId());
    }

    @Test
    @Flaky
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новостей")
    public void addNewsItemTest() {
        mainPage.waitMainPage();
        String newsItemName = TestUtilities.getRandomNewsItem();
        MainPage.clickButton(MainPage.getAllNewsButtonId());
        NewsPage.clickButton(NewsPage.getEditButtonId());
        NewsPage.clickButton(NewsPage.getAddNewsButtonId());
        createNewsPage.chooseCategoryAndTitle(TITLE);
        createNewsPage.addNewsDate();
        createNewsPage.addNewsTime();
        createNewsPage.addNewsDescription(newsItemName);
        CreateNewsPage.clickButtonWithScroll(CreateNewsPage.getSaveButtonId());
        mainPage.chooseMainMenuItem(ITEM_NEWS);
        newsPage.checkAddedNews(newsItemName, 0);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление через главное меню")
    public void addNewsItemTestFromMainMenu() {
        mainPage.waitMainPage();
        String newsItemName = TestUtilities.getRandomNewsItem();
        mainPage.chooseMainMenuItem(ITEM_NEWS);
        NewsPage.clickButton(NewsPage.getEditButtonId());
        NewsPage.clickButton(NewsPage.getAddNewsButtonId());
        createNewsPage.chooseCategoryAndTitle(TITLE);
        createNewsPage.addNewsDate();
        createNewsPage.addNewsTime();
        createNewsPage.addNewsDescription(newsItemName);
        CreateNewsPage.clickButtonWithScroll(CreateNewsPage.getSaveButtonId());
        mainPage.chooseMainMenuItem(ITEM_MAIN);
        MainPage.clickButton(MainPage.getAllNewsButtonId());
        newsPage.checkAddedNews(newsItemName, 0);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новостей c неверной датой")
    public void addNewsItemWithWrongDateTest() {   //должен падать так как нет сообщения о не корректной дате: "Wrong date!"
        mainPage.waitMainPage();
        String newsItemName = TestUtilities.getRandomNewsItem();
        MainPage.clickButton(MainPage.getAllNewsButtonId());
        NewsPage.clickButton(NewsPage.getEditButtonId());
        NewsPage.clickButton(NewsPage.getAddNewsButtonId());
        createNewsPage.chooseCategoryAndTitle(TITLE);
        createNewsPage.addNewsDateWithPaste(WRONG_DATE);
        createNewsPage.addNewsTime();
        createNewsPage.addNewsDescription(newsItemName);
        CreateNewsPage.clickButtonWithScroll(CreateNewsPage.getSaveButtonId());
        CreateNewsPage.checkToastMessage(ERROR_MESSAGE_1, decorView);
    }
}
