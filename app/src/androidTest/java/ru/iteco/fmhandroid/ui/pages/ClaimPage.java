package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
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

    public void checkAddedClaim(String description) {
        Allure.step("Проверка наличия добавленной заявки c описанием: " + description);
        onView(ViewMatchers.withId(listOfClaims))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(description)))).check(matches(isDisplayed()));
    }
}
