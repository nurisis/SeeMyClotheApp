package com.nurisis.seemyclothappp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ClothesListFragmentTest {

    var test = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfProgressBarIsDisplayedFirst() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfTestViewIsNotDisplayedOnLaunch() {
        onView(withId(R.id.text)).check(matches(not(isDisplayed())))
    }

}