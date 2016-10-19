package freeJokes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.Joker;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jokes.jokeandroidlibrary.Constants;
import com.jokes.jokeandroidlibrary.JokeShowActivity;
import com.jokes.onlinejokes.R;
import com.jokes.onlinejokes.network.DataManager;
import com.jokes.onlinejokes.network.NetworkBackgroundTask;
import com.jokes.onlinejokes.utility.Utils;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class JokeTellingFragment extends Fragment
    implements NetworkBackgroundTask.OnServerResponse {

  private static final String TAG = JokeTellingFragment.class.getSimpleName();
  private InterstitialAd mInterstitialAd;
  private FloatingActionButton jokeButton;
  private Context mContext;
  private String mJoke;
  private ProgressBar mProgressBar;
  public boolean adremoved;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_joke, container, false);
    jokeButton = (FloatingActionButton) root.findViewById(R.id.jokeButton);
    mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
    jokeButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (Utils.isConnectedToNetwork(getActivity())) {
          mProgressBar.setVisibility(View.VISIBLE);
          requestNewInterstitial();
          DataManager.getInstance()
              .getJokesFromServer(JokeTellingFragment.this, getActivity(), Constants.FREE_JOKE_ID);
        } else {
          showJoke(new Joker().getOfflinejokes());
        }
      }
    });
    mInterstitialAd = new InterstitialAd(getActivity());
    mInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.adUnitId));

    mInterstitialAd.setAdListener(new AdListener() {

      @Override public void onAdLoaded() {
        super.onAdLoaded();
        showInterstitial();
      }

      @Override public void onAdClosed() {
        adremoved = true;
        showJoke(mJoke);
      }
    });
    requestNewInterstitial();

    return root;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  private void showJoke(String joke) {
    if (!isAdded() || !adremoved) {
      return;
    }
    Intent intent = new Intent(mContext, JokeShowActivity.class);
    intent.putExtra(Constants.JOKE_ID, joke);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(intent);
    adremoved = false;
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
    mProgressBar.setVisibility(View.GONE);
    mJoke = res;
    showJoke(res);
  }
}
