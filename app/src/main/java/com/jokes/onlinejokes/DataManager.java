package com.jokes.onlinejokes;

import android.content.Context;
import android.util.Pair;
import freeJokes.JokeTellingFragment;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class DataManager {

  private static final DataManager INSTANCE = new DataManager();

  private DataManager() {
    if (INSTANCE != null) {
      throw new IllegalStateException("Already instantiated");
    }
  }

  public static DataManager getInstance() {
    return INSTANCE;
  }

  public void getJokesFromServer(JokeTellingFragment fragment, Context context,String token) {
    new NetworkBackgroundTask(fragment,context).execute(new Pair<Context, String>(context, token));
  }
}
