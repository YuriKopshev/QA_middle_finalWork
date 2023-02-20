package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class ClaimPage extends EspressoBaseTest {

    private final int listOfClaims = R.id.claim_list_recycler_view;


    public static int addNewClaimButtonId() {
        return R.id.add_new_claim_material_button;
    }

    public static int getAllClaimButtonId() {
        return R.id.all_claims_text_view;
    }

    @Step(value = "Проверка наличия добавленной заявки")
    public void checkAddedClaim(String description) {
        onView(ViewMatchers.withId(listOfClaims))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(description)))).check(matches(isDisplayed()));
    }
}
