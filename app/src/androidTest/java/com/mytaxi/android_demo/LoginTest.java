package com.mytaxi.android_demo;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
                try {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withText(logoutButton)).perform(click());
        } catch (NoMatchingViewException e) {
        }
    }

    private String username = "whiteelephant261";
    private String password = "video1";
    private String failedLoginMessage = "Login failed";
    private String logoutButton = "Logout";
    private String inputLetters = "sa";
    private String driverName = "Sarah Friedrich";

    @Test
    public void correctLoginTest() {
        onView(withId(R.id.edt_username)).perform(replaceText(username));
        onView(withId(R.id.edt_password)).perform(replaceText(password));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
    }

    @Test
    public void incorrectLoginTest() {
        onView(withId(R.id.edt_username)).perform(replaceText(UUID.randomUUID().toString()));
        onView(withId(R.id.edt_password)).perform(replaceText(UUID.randomUUID().toString()));
        onView(withId(R.id.btn_login)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.searchContainer)).check(doesNotExist());
    }

    @After
    public void tearDown () throws Exception {

    }
}
