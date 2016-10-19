package com.example;

import java.util.Random;

/**
 * Created by harshikesh.kumar on 14/10/16.
 */
public class Joker {

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
      + "\n",
      " I find it ironic that the colors red, white, and blue stand for freedom until they are flashing behind you."
  };

  public String getOfflinejokes() {
    return jokes[0];
  }

  public String tellaJoke() {
    Random random = new Random();
    int val = random.nextInt(3);
    return jokes[val];
  }
}
