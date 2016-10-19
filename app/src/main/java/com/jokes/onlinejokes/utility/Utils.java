package com.jokes.onlinejokes.utility;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.PermissionChecker;

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

  public static boolean hasPermissions(Context context, String[] perms) {
    boolean permGranted = true;
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      permGranted = true;
    } else {
      for (String perm : perms) {
        permGranted &= hasPermission(context, perm);
      }
    }
    return permGranted;
  }

  public static boolean hasPermission(Context context, String perm) {
    boolean permissionGranted = true;
    try {
      permissionGranted = PermissionChecker.checkSelfPermission(context, perm)
          == PackageManager.PERMISSION_GRANTED;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return permissionGranted;
  }
}
