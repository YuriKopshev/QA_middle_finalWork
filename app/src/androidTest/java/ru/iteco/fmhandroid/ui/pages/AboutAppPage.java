package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class AboutAppPage extends EspressoBaseTest {

    private final int appVersionId = R.id.about_version_value_text_view;
    private final int appDevId = R.id.about_company_info_label_text_view;

    public static int getBackIdButton() {
        return R.id.about_back_image_button;
    }

    public void checkAppVersion(String version) {
        Allure.step("Проверка отображения информации о версии приложения c номером: " + version);
        onView(allOf(withId(appVersionId), withText(version))).check(matches(isDisplayed()));
    }

    public void checkAppDeveloper(String info) {
        Allure.step("Проверка отображения информации о приложении с содержанием: " + info);
        onView(allOf(withId(appDevId), withText(info))).check(matches(isDisplayed()));
    }
}
