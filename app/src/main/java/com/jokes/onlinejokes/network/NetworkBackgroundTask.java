package com.jokes.onlinejokes.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.example.udacity.onlinejokes.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jokes.onlinejokes.R;
import java.io.IOException;

/**
 * Created by harshikesh.kumar on 18/10/16.
 */
public class NetworkBackgroundTask extends AsyncTask<Pair<Context, String>, Void, String> {

  private static final String TAG = NetworkBackgroundTask.class.getSimpleName();
  private static MyApi myApiService = null;
  private Context context;
  private String name;

  OnServerResponse mOnServerResponse;

  public NetworkBackgroundTask(OnServerResponse onServerResponse,Context cont){
    mOnServerResponse = onServerResponse;
    context = cont;
  }

  @Override protected String doInBackground(Pair<Context, String>... params) {
    if (myApiService == null) {  // Only do this once
      MyApi.Builder builder =
          new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),
              null).setRootUrl(context.getResources().getString(
              R.string.joke_endpoint)).setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
        @Override
        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws
            IOException {
          abstractGoogleClientRequest.setDisableGZipContent(true);
        }
      });
      myApiService = builder.build();
    }

    context = params[0].first;
    name = params[0].second;
    Log.d(TAG, "name :" + name);
    try {
      return myApiService.sayHi(name).execute().getData();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override protected void onPostExecute(String result) {
    mOnServerResponse.onDataRecieved(result);
  }

  public interface  OnServerResponse{
    void onDataRecieved(String res);
  }
}