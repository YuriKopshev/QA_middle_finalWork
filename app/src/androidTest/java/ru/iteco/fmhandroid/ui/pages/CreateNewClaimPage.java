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

import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class CreateNewClaimPage {
    private final int titleField = R.id.title_edit_text;
    private final int dateField = R.id.date_in_plan_text_input_edit_text;
    private final int timeField = R.id.time_in_plan_text_input_edit_text;
    private final int descriptionField = R.id.description_edit_text;
    private final int errorMessageId = android.R.id.message;
    private final int saveButtonId = R.id.save_button;
    private final int cancelTimeInputButtonId = android.R.id.button2;
    private final int cancelButtonId = R.id.cancel_button;
    private final int okButtonId = android.R.id.button1;

    public void addClaimTitle(String name) {
        Allure.step("Добавления заголовка к заявке с текстом: " + name);
        onView(withId(titleField)).perform(click())
                .perform(replaceText(name), closeSoftKeyboard());
    }

    public void addClaimDate() {
        Allure.step("Выбор даты заявки");
        onView(withId(dateField)).perform(click());
        onView(withId(okButtonId)).perform(scrollTo(), click());
    }

    public void addClaimDateWithPaste(String date) {
        Allure.step("Добавление даты заявки с вставкой текста:" + date);
        onView(withId(dateField)).perform(longClick())
                .perform(replaceText(date), closeSoftKeyboard());
    }

    public void addClaimTime() {
        Allure.step("Выбор времени заявки");
        onView(withId(timeField)).perform(click());
        onView(withId(okButtonId)).perform(scrollTo(), click());
    }

    public void addClaimDescription(String description) {
        Allure.step("Добавления описания к заявке с текстом: " + description);
        onView(withId(descriptionField)).perform(click())
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public void addTimeWithInput(String hour) {
        Allure.step("Добавление времени заявки с вставкой текста:" + hour);
        onView(withId(timeField)).perform(click());
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."))).perform(click());
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(allOf(withClassName(is("android.widget.RelativeLayout")),
                        childAtPosition(withClassName(is("android.widget.TextInputTimePickerView")), 1)), 0)))
                .perform(replaceText(hour), closeSoftKeyboard());
        onView(withId(okButtonId)).perform(scrollTo(), click());
    }

    public void checkTimeErrorNotification(String message) {
        Allure.step("Проверка отображения сообщения об ошибке при вводе времени с текстом: " + message);
        onView(allOf(IsInstanceOf.instanceOf(android.widget.TextView.class),
                withText(message)))
                .check(matches(isDisplayed()));
    }

    public void checkErrorMessage(String emptyFieldErrorMessage) {
        Allure.step("Проверка отображения сообщения об ошибке при вводе неверных данных заявки" +
                " с текстом ошибки: " + emptyFieldErrorMessage);
        onView(allOf(withId(errorMessageId), withText(emptyFieldErrorMessage))).check(matches(isDisplayed()));
    }

    public void clickSaveButtonIdWithScroll() {
        Allure.step("Клик со скроллом по кнопке c id: " + saveButtonId);
        onView((withId(saveButtonId))).perform(scrollTo(), click());
    }

    public void clickOkButton() {
        Allure.step("Клик по кнопке c id: " + okButtonId);
        onView((withId(okButtonId))).perform(click());
    }

    public void clickCancelTimeInputButton() {
        Allure.step("Клик по кнопке c id: " + cancelTimeInputButtonId);
        onView((withId(cancelTimeInputButtonId))).perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Клик по кнопке c id: " + cancelButtonId);
        onView((withId(cancelButtonId))).perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Клик по кнопке c id: " + saveButtonId);
        onView((withId(saveButtonId))).perform(click());
    }

    public void clickCancelButtonWithScroll() {
        Allure.step("Клик со скроллом по кнопке c id: " + cancelButtonId);
        onView((withId(cancelButtonId))).perform(scrollTo(), click());
    }
}
