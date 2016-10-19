package com.jokes.onlinejokes;

import android.content.Context;
import android.os.ConditionVariable;
import android.test.AndroidTestCase;
import android.text.TextUtils;
import android.util.Pair;
import com.jokes.onlinejokes.network.NetworkBackgroundTask;
import com.jokes.onlinejokes.ui.MainActivity;

public class JokeAsyncAndroidTest extends AndroidTestCase
    implements NetworkBackgroundTask.OnServerResponse {

  private NetworkBackgroundTask jokesAsyncTask;
  private ConditionVariable waiter;

  @Override protected void setUp() throws Exception {
    super.setUp();
    jokesAsyncTask = new NetworkBackgroundTask(JokeAsyncAndroidTest.this, getContext());
    waiter = new ConditionVariable();
  }

  public void testJokeIsNotEmpty() {
    jokesAsyncTask.execute(new Pair<Context, String>(getContext(), "freeJoke"));
    waiter.block();
  }

  @Override public void onDataRecieved(String res) {
    assertFalse(TextUtils.isEmpty(res));
    waiter.open();
  }
}