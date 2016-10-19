package com.jokes.onlinejokes.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.Joker;
import com.jokes.onlinejokes.R;
import com.jokes.onlinejokes.utility.Utils;
import freeJokes.JokeTellingFragment;

public class MainActivity extends AppCompatActivity {

  public static final String[] PERMISSIONS = {
      Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      getSupportFragmentManager().beginTransaction().
          add(R.id.frame_layout, new JokeTellingFragment()).commit();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }


}
