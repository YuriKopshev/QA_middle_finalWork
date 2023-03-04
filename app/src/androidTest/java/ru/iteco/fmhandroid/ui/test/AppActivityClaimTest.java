package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

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
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.ClaimPage;
import ru.iteco.fmhandroid.ui.pages.CreateNewClaimPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.utils.AuthorizationData;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppActivityClaimTest extends BaseTest {
    private static final String WRONG_HOUR = "25";
    private static final String TIME_ERROR_MESSAGE = "Enter a valid time";
    private final static String WRONG_DATE = "01.01.0001";
    private static final String DATE_ERROR_MESSAGE = "Enter a valid date";
    private static final String EMPTY_FIELD_ERROR_MESSAGE = "Fill empty fields";
    private final static AuthorizationPage authorizationPage = new AuthorizationPage();
    private final static MainPage mainPage = new MainPage();
    private final CreateNewClaimPage newClaimPage = new CreateNewClaimPage();
    private final ClaimPage claimPage = new ClaimPage();

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
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки")
    public void createNewClaimTest() {
        mainPage.waitMainPage();
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        claimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDate();
        newClaimPage.addClaimTime();
        newClaimPage.addClaimDescription(TestUtilities.getRandomComment());
        newClaimPage.clickButtonWithScroll(CreateNewClaimPage.getSaveButtonId());
        claimPage.clickButton(ClaimPage.getAllClaimButtonId());
        claimPage.checkAddedClaim(claimName);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с неверным временем")
    public void createNewClaimWithWrongTimeTest() {
        mainPage.waitMainPage();
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        claimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDate();
        newClaimPage.addTimeWithInput(WRONG_HOUR);
        newClaimPage.checkTimeErrorNotification(TIME_ERROR_MESSAGE);
        newClaimPage.clickButton(CreateNewClaimPage.getCancelTimeInputButtonId());
        newClaimPage.clickButton(CreateNewClaimPage.getCancelButtonId());
        newClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с неверной датой")
    public void createNewClaimWithWrongDateTest() {  //падает так как принимает несуществ. дату
        mainPage.waitMainPage();
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        claimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDateWithPaste(WRONG_DATE);
        newClaimPage.addClaimTime();
        newClaimPage.addClaimDescription(TestUtilities.getRandomComment());
        newClaimPage.clickButtonWithScroll(CreateNewClaimPage.getSaveButtonId());
        newClaimPage.checkErrorMessage(DATE_ERROR_MESSAGE);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с незаполненными полями ввода")
    public void createNewClaimWithEmptyFieldsTest() {
        mainPage.waitMainPage();
        mainPage.clickAllClaimButtonId();
        claimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.clickButton(CreateNewClaimPage.getSaveButtonId());
        newClaimPage.checkErrorMessage(EMPTY_FIELD_ERROR_MESSAGE);
        newClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
        newClaimPage.clickButtonWithScroll(CreateNewClaimPage.getCancelButtonId());
        newClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
    }
}
