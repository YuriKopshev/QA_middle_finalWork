package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.utils.TestUtilities.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;
import ru.iteco.fmhandroid.ui.utils.TestUtilities;

public class CreateNewClaimPage extends EspressoBaseTest {
    private final int titleField = R.id.title_edit_text;
    private final int dateField = R.id.date_in_plan_text_input_edit_text;
    private final int timeField = R.id.time_in_plan_text_input_edit_text;
    private final int descriptionField = R.id.description_edit_text;
    private final int errorMessageId = android.R.id.message;

    @Step(value = "Добавления заголовка к заявке")
    public void addClaimTitle(String name) {
        onView(withId(titleField)).perform(click())
                .perform(replaceText(name), closeSoftKeyboard());
    }

    @Step(value = "Choose claim date")
    public void addClaimDate() {
        onView(withId(dateField)).perform(click());
        onView(withId(getOkButtonId())).perform(scrollTo(), click());
    }

    @Step(value = "Add claim date with paste")
    public void addClaimDateWithPaste(String date) {
        onView(withId(dateField)).perform(longClick())
                .perform(replaceText(date), closeSoftKeyboard());
    }

    @Step(value = "Choose claim time")
    public void addClaimTime() {
        onView(withId(timeField)).perform(click());
        onView(withId(getOkButtonId())).perform(scrollTo(), click());
    }

    @Step(value = "Добавления описания к заявке")
    public void addClaimDescription(String description) {
        onView(withId(descriptionField)).perform(click())
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public static int getSaveButtonId() {
        return R.id.save_button;
    }

    public static int getCancelTimeInputButtonId() {
        return android.R.id.button2;
    }

    public static int getCancelButtonId() {
        return R.id.cancel_button;
    }

    public static int getOkButtonId() {
        return android.R.id.button1;
    }

    @Step(value = "Choose claim time handle")
    public void addTimeWithInput(String hour) {
        onView(withId(timeField)).perform(click());
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."))).perform(click());
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(allOf(withClassName(is("android.widget.RelativeLayout")),
                        childAtPosition(withClassName(is("android.widget.TextInputTimePickerView")), 1)), 0)))
                .perform(replaceText(hour), closeSoftKeyboard());
        onView(withId(getOkButtonId())).perform(scrollTo(), click());
    }

    @Step(value = "Проверка отображения сообщения об ошибке при вводе времени")
    public void checkTimeErrorNotification(String message) {
        onView(allOf(IsInstanceOf.instanceOf(android.widget.TextView.class),
                withText(message)))
                .check(matches(isDisplayed()));
    }

    @Step(value = "Проверка отображения сообщения об ошибке пр вводе данных о заявке")
    public void checkErrorMessage(String emptyFieldErrorMessage) {
        onView(allOf(withId(errorMessageId), withText(emptyFieldErrorMessage))).check(matches(isDisplayed()));
    }
}
