package com.jokes.onlinejokes;

import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;
import com.example.Joker;
import com.jokes.onlinejokes.ui.MainActivity;
import android.content.Intent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
  @Rule
  public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

  @Test
  public void retrieveJokeOnClick() {
    onView(withId(com.jokes.onlinejokes.R.id.jokeButton)).perform(click());
    intended(hasComponent(hasClassName(Joker.class.getName())));
    intended(hasExtra(equalTo(Intent.EXTRA_TEXT), notNullValue()));
  }
}