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
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.ClaimPage;
import ru.iteco.fmhandroid.ui.pages.CreateNewClaimPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppActivityClaimTest {
    private static final String LOGIN = "login2";
    private static final String PASSWORD = "password2";
    private static final String WRONG_HOUR = "25";
    private static final String TIME_ERROR_MESSAGE = "Enter a valid time";
    private final static String WRONG_DATE = "01.01.0001";
    private static final String DATE_ERROR_MESSAGE = "Enter a valid date";
    private static final String EMPTY_FIELD_ERROR_MESSAGE = "Fill empty fields";
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final CreateNewClaimPage newClaimPage = new CreateNewClaimPage();
    private final ClaimPage claimPage = new ClaimPage();

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
    @Description(value = "Тест проверяет добавление новой заявки")
    public void createNewClaimTest() {
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogoId(), 5000));
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        ClaimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDate();
        newClaimPage.addClaimTime();
        newClaimPage.addClaimDescription(TestUtilities.getRandomComment());
        CreateNewClaimPage.clickButtonWithScroll(CreateNewClaimPage.getSaveButtonId());
        ClaimPage.clickButton(ClaimPage.getAllClaimButtonId());
        claimPage.checkAddedClaim(claimName);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с неверным временем")
    public void createNewClaimWithWrongTimeTest() {
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogoId(), 5000));
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        ClaimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDate();
        newClaimPage.addTimeWithInput(WRONG_HOUR);
        newClaimPage.checkTimeErrorNotification(TIME_ERROR_MESSAGE);
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getCancelTimeInputButtonId());
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getCancelButtonId());
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с неверной датой")
    public void createNewClaimWithWrongDateTest() {  //падает так как принимает несуществ. дату
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogoId(), 5000));
        String claimName = TestUtilities.getRandomClaimName();
        mainPage.clickAllClaimButtonId();
        ClaimPage.clickButton(ClaimPage.addNewClaimButtonId());
        newClaimPage.addClaimTitle(claimName);
        newClaimPage.addClaimDateWithPaste(WRONG_DATE);
        newClaimPage.addClaimTime();
        newClaimPage.addClaimDescription(TestUtilities.getRandomComment());
        CreateNewClaimPage.clickButtonWithScroll(CreateNewClaimPage.getSaveButtonId());
        newClaimPage.checkErrorMessage(DATE_ERROR_MESSAGE);
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест проверяет добавление новой заявки с незаполненными полями ввода")
    public void createNewClaimWithEmptyFieldsTest() {
        onView(isRoot()).perform(waitDisplayed(MainPage.getLogoId(), 5000));
        mainPage.clickAllClaimButtonId();
        ClaimPage.clickButton(ClaimPage.addNewClaimButtonId());
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getSaveButtonId());
        newClaimPage.checkErrorMessage(EMPTY_FIELD_ERROR_MESSAGE);
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
        CreateNewClaimPage.clickButtonWithScroll(CreateNewClaimPage.getCancelButtonId());
        CreateNewClaimPage.clickButton(CreateNewClaimPage.getOkButtonId());
    }
}
