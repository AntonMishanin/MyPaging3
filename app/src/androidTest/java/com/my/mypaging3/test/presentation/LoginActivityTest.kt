package com.my.mypaging3.test.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.my.mypaging3.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/*
@LargeTest
@RunWith(AndroidJUnit4::class)
internal class LoginActivityTest {
    private lateinit var scenario: ActivityScenario<LoginActivity>

    @Before
    fun before() {
        scenario = launch(LoginActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun success_state_should_be_gone_on_the_start() {
        onView(withId(R.id.success))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun error_state_should_be_gone_on_the_start() {
        onView(withId(R.id.error))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun error_state_should_be_visible_if_email_is_wrong() {
        val wrongEmail = ""
        onView(withId(R.id.email)).perform(ViewActions.typeText(wrongEmail))

        Espresso.closeSoftKeyboard()
        onView(withId(R.id.login)).perform(ViewActions.click())

        onView(withId(R.id.success)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.error)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun success_state_should_be_visible_if_email_is_correct() {
        val correctMail = "correctMail@.ru"
        onView(withId(R.id.email)).perform(ViewActions.typeText(correctMail))

        Espresso.closeSoftKeyboard()
        onView(withId(R.id.login)).perform(ViewActions.click())

        onView(withId(R.id.success)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.error)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}*/
