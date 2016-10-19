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
import freeJokes.JokeTellingFragment;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_READ_PHONE_STATE = 1;
  String readPermission = android.Manifest.permission.READ_PHONE_STATE;
  String networkPermission = Manifest.permission.ACCESS_NETWORK_STATE;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    int permissionCheck =
        ContextCompat.checkSelfPermission(getApplicationContext(), readPermission);
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[] { readPermission, networkPermission },
          REQUEST_READ_PHONE_STATE);
    } else {
      getSupportFragmentManager().beginTransaction().
          add(R.id.frame_layout, new JokeTellingFragment()).commit();
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override public void onRequestPermissionsResult(int requestCode, String permissions[],
      int[] grantResults) {
    switch (requestCode) {
      case REQUEST_READ_PHONE_STATE:
        if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
        //
        }
        break;

      default:
        finish();
        break;
    }
  }
}
