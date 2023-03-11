package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.waitDisplayed;

import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    private final int mainMenuButton = R.id.main_menu_image_button;
    private final int logoId = R.id.trademark_image_view;
    private final int titleLogOutId = android.R.id.title;
    private final int logOutButtonId = R.id.authorization_image_button;
    private final int allNewsButtonId = R.id.all_news_text_view;
    private final int missionTitleId = R.id.our_mission_title_text_view;
    private final int missionButtonId = R.id.our_mission_image_button;

    public void waitMainPage() {
        onView(isRoot()).perform(waitDisplayed(logoId, 5000));
    }

    public void waitLogOutButton() {
        onView(isRoot()).perform(waitDisplayed(logOutButtonId, 5000));
    }

    public void clickAllClaimButtonId() {
        Allure.step("Клик по кнопке Все заявки");
        onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)))
                .perform(click());
    }

    public void chooseMainMenuItem(String item) {
        Allure.step("Выбор пункта главного меню с названием: " + item);
        onView(withId(mainMenuButton)).perform(click());
        onView(withText(item))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
    }

    public void checkTextById(Integer resourceId, String text) {
        Allure.step("Проверка отображения текста: " + text + " на странице по id: " + resourceId);
        onView((withId(resourceId))).check(matches(withText(text)));
    }

    public void clickLogOutButton() {
        Allure.step("Клик по кнопке c id: " + logOutButtonId);
        onView((withId(logOutButtonId))).perform(click());
    }

    public void clickTitleLogOutButton() {
        Allure.step("Клик по кнопке c id: " + titleLogOutId);
        onView((withId(titleLogOutId))).perform(click());
    }

    public void checkLogoId() {
        Allure.step("Проверка отображения элемента на странице c id: " + logoId);
        onView(withId(logoId)).check(matches(isDisplayed()));
    }

    public void clickAllNewsButton() {
        Allure.step("Клик по кнопке c id: " + allNewsButtonId);
        onView((withId(allNewsButtonId))).perform(click());
    }

    public void clickMissionButton() {
        Allure.step("Клик по кнопке c id: " + missionButtonId);
        onView((withId(missionButtonId))).perform(click());
    }

    public int getMissionTitleId() {
        return missionTitleId;
    }
}
