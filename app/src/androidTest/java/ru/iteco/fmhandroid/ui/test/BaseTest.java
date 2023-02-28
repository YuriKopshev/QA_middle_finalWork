package ru.iteco.fmhandroid.ui.test;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;

import ru.iteco.fmhandroid.ui.AppActivity;

public class BaseTest {

    public View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setDecorView() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<ru.iteco.fmhandroid.ui.AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

}
