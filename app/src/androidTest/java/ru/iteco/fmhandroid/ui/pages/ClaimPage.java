package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ClaimPage {
    private final int listOfClaims = R.id.claim_list_recycler_view;
    private final int newClaimButtonId = R.id.add_new_claim_material_button;
    private final int allClaimButtonId = R.id.all_claims_text_view;

    public void checkAddedClaim(String description) {
        Allure.step("Проверка наличия добавленной заявки c описанием: " + description);
        onView(ViewMatchers.withId(listOfClaims))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(description)))).check(matches(isDisplayed()));
    }

    public void clickNewClaimButton() {
        Allure.step("Клик по кнопке c id: " + newClaimButtonId);
        onView((withId(newClaimButtonId))).perform(click());
    }

    public void clickAllClaimButton() {
        Allure.step("Клик по кнопке c id: " + allClaimButtonId);
        onView((withId(allClaimButtonId))).perform(click());
    }
}
