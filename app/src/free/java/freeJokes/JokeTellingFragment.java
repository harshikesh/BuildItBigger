package freeJokes;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jokes.jokeandroidlibrary.Constants;
import com.jokes.jokeandroidlibrary.JokeShowActivity;
import com.jokes.onlinejokes.DataManager;
import com.jokes.onlinejokes.Manifest;
import com.jokes.onlinejokes.R;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class JokeTellingFragment extends Fragment
    implements NetworkBackgroundTask.OnServerResponse {

  private static final String TAG = JokeTellingFragment.class.getSimpleName();
  InterstitialAd mInterstitialAd;
  private FloatingActionButton jokeButton;
  private Context mContext;
  private String mJoke;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_joke, container, false);
    jokeButton = (FloatingActionButton) root.findViewById(R.id.jokeButton);
    jokeButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        requestNewInterstitial();
        DataManager.getInstance()
            .getJokesFromServer(JokeTellingFragment.this, getActivity(), Constants.FREE_JOKE_ID);
      }
    });
    mInterstitialAd = new InterstitialAd(getActivity());
    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

    mInterstitialAd.setAdListener(new AdListener() {
      @Override public void onAdLoaded() {
        super.onAdLoaded();
        showInterstitial();
      }

      @Override public void onAdClosed() {
        showJoke();
      }
    });
    requestNewInterstitial();

    return root;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  private void showJoke() {
    if (!isAdded()) {
      return;
    }
    Intent intent = new Intent(mContext, JokeShowActivity.class);
    intent.putExtra(Constants.JOKE_ID, mJoke);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(intent);
  }

  private void requestNewInterstitial() {

    String deviceId =
        ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    Log.d(TAG, "device id : " + deviceId);
    AdRequest adRequest = new AdRequest.Builder().addTestDevice(deviceId).build();
    mInterstitialAd.loadAd(adRequest);
  }
  private void showInterstitial() {
    if (mInterstitialAd.isLoaded()) {
      mInterstitialAd.show();
    }
  }


  @Override public void onDataRecieved(String res) {
    mJoke = res;
  }
}
