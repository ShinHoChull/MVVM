package com.m2comm.test.main;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

import static java.util.regex.Pattern.matches;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.m2comm.test.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void oneButtonClick() {

        ActivityScenario scenario = rule.getScenario();
        Espresso.onView(ViewMatchers.withId(R.id.bt0))
                .check(matches(isDisplayed()))

        Espresso
                .onView(ViewMatchers.withId(R.id.sum_textview))
                .check(ViewAssertions.matches(ViewMatchers.withText("0")));

// 버튼을 클릭하게 만들어 Toast가 나타나도록한다.(토스트는 앞선 MainActivity에 버튼 행동 정의)
        Espresso
                .onView(ViewMatchers.withId(R.id.bt0))
                .perform(ViewActions.click());


    }


}