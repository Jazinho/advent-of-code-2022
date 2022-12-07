package com.jpalucki;

import java.util.*;

public class Day3Main2 {


  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    List<String> badgesItems = new LinkedList<String>();

    String[] rucksacks = INPUT.split("\n");
    for (int i = 0; i < rucksacks.length; i+=3) {
      Set<String> firstElfItems = new HashSet<String>(Arrays.asList(rucksacks[i].split("")));
      Set<String> secondElfItems = new HashSet<String>(Arrays.asList(rucksacks[i+1].split("")));

      for (String item : rucksacks[i+2].split("")) {
        if (firstElfItems.contains(item) && secondElfItems.contains(item)) {
          badgesItems.add(item);
          break;
        }
      }
    }

    System.out.println(badgesItems.size());
    System.out.println(badgesItems);
    int result = 0;
    for (String s: badgesItems) {
      result += getPriorityForItem(s);
    }

    System.out.println(result);
  }

  private static int getPriorityForItem(String item) {
    int codePoint = item.charAt(0);
    if (codePoint < 97) {
      codePoint -= 38;
    } else {
      codePoint -= 96;
    }
    return codePoint;
  }

}
