package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class MainPage extends EspressoBaseTest {
    private final int mainMenuButton = R.id.main_menu_image_button;

    public static int getLogoId() {
        return R.id.trademark_image_view;
    }

    public static int getTitleLogOutId() {
        return android.R.id.title;
    }

    public static int getLogOutButtonId() {
        return R.id.authorization_image_button;
    }

    public static int getAllNewsButtonId() {
        return R.id.all_news_text_view;
    }

    public static int getMissionTitleId() {
        return R.id.our_mission_title_text_view;
    }
    public static int getMissionButtonId(){
        return R.id.our_mission_image_button;
    }

    @Step(value = "кликкуть по кнопке Все заявки")
    public void clickAllClaimButtonId() {
        onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)))
                .perform(click());
    }

    @Step(value = "Выбрать главное меню")
    public void chooseMainMenuItem(String item) {
        onView(withId(mainMenuButton)).perform(click());
        onView(withText(item))
                .inRoot(RootMatchers.isPlatformPopup())      //DropDown value selection
                .perform(click());
    }

}
