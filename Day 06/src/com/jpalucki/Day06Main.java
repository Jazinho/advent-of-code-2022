package com.jpalucki;

import java.util.*;

public class Day06Main {

  private final static int MAX_PACKET_STARTER_BUFFER_SIZE = 14;
  private final static int MAX_MESSAGE_BUFFER_SIZE = 14;

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    LinkedList<String> checkBuffer = new LinkedList<String>();
    String[] chars = INPUT.split("");
    for (int i = 0; i < chars.length; i++) {
      if(checkBuffer.size() == MAX_MESSAGE_BUFFER_SIZE) {
        if (containsMessageStartMarker(checkBuffer)) {
          System.out.println("Found marker position at index: " + i);
          break;
        } else {
          checkBuffer.pop();
        }
      }

      checkBuffer.add(chars[i]);
    }
  }

  private static boolean containsPacketStartMarker(List<String> list) {
    return new HashSet<String>(list).size() == MAX_PACKET_STARTER_BUFFER_SIZE;
  }

  private static boolean containsMessageStartMarker(List<String> list) {
    return new HashSet<String>(list).size() == MAX_MESSAGE_BUFFER_SIZE;
  }
}
