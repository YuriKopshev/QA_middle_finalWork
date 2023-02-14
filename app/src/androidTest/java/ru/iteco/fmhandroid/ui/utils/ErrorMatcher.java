package ru.iteco.fmhandroid.ui.utils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ErrorMatcher {
    @NonNull
    public static Matcher<View> withError(final String expectedErrorText) {
        Checks.checkNotNull(expectedErrorText);
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            public void describeTo(final Description description) {
                description.appendText("error text: ");
                //stringMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(final TextView textView) {
                return expectedErrorText.equals(textView.getError().toString());
            }
        };
    }
}


