package com.jokes.jokeandroidlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class JokeShowActivity extends AppCompatActivity {

  private TextView tvview;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_showjoke);
    String joke = getIntent().getStringExtra(Constants.JOKE_ID);
    tvview = (TextView) findViewById(R.id.textview);
    tvview.setText(joke);
  }
}
