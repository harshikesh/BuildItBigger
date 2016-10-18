package freeJokes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.jokes.jokeandroidlibrary.Constants;
import com.jokes.jokeandroidlibrary.JokeShowActivity;
import com.jokes.onlinejokes.DataManager;
import com.jokes.onlinejokes.NetworkBackgroundTask;
import com.jokes.onlinejokes.R;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class JokeTellingFragment extends Fragment
    implements NetworkBackgroundTask.OnServerResponse {

  private static final String TAG = JokeTellingFragment.class.getSimpleName();
  private FloatingActionButton jokeButton;
  private Context mContext;
  private String mJoke;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_joke, container, false);
    jokeButton = (FloatingActionButton) root.findViewById(R.id.jokeButton);
    jokeButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DataManager.getInstance()
            .getJokesFromServer(JokeTellingFragment.this, getActivity(), Constants.PAID_JOKE_ID);
      }
    });
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

  @Override public void onDataRecieved(String res) {
    mJoke = res;
    showJoke();
  }
}