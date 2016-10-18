/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.udacity.onlinejokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import java.util.Random;
import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
    name = "myApi",
    version = "v1",
    namespace = @ApiNamespace(
        ownerDomain = "backend.onlinejokes.udacity.example.com",
        ownerName = "backend.onlinejokes.udacity.example.com",
        packagePath = "")) public class MyEndpoint {

  public static final String FREE_JOKE_ID = "freeJoke";
  public static final String PAID_JOKE_ID = "paidJoke";

  String[] jokes = {
      "\n"
          + "What is the difference between a snowman and a snowwoman?\n"
          + "-\n"
          + "Snowballs.\n"
          + "\n",
      "Why is it a bad idea for two butt cheeks to get married? Because they part for every little shit.\n"
          + "\n", "Police officer: \"Can you identify yourself, sir?\"\n"
      + " \n"
      + "Driver pulls out his mirror and says: \"Yes, it's me.\"\n"
      + "\n"
      + "\n"
  };

  /** A simple endpoint method that takes a name and says Hi back */
  @ApiMethod(name = "sayHi") public MyBean sayHi(@Named("name") String name) {
    System.out.println("sayHi : "+name);
    MyBean response = new MyBean();
    Random random = new Random();
    if (name.equalsIgnoreCase(PAID_JOKE_ID)) {
      int val = random.nextInt(2);
      response.setData(jokes[val]);
    } else {
      response.setData(jokes[0]);
    }

    return response;
  }
}
