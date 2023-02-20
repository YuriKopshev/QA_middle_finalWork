package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class MissionPage extends EspressoBaseTest {
    private final int missionItemTitleId = R.id.our_mission_item_title_text_view;
    private final int missionItemMaterialCardId = R.id.our_mission_item_material_card_view;

    @Step(value = "Проверка отображения цитаты с названием {text}")
    public void checkMissionItem(String text) {
        onView(allOf(withId(missionItemTitleId), withText(text),
                withParent(withParent(withId(missionItemMaterialCardId))),
                isDisplayed()));
    }

}
