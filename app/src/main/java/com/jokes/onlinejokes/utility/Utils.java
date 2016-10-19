package com.jokes.onlinejokes.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by harshikesh.kumar on 19/10/16.
 */
public class Utils {

  public static boolean isConnectedToNetwork(Context context){
      ConnectivityManager connManager =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (null != connManager) {
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
      }
      return false;
    }
}
