package com.jpalucki;

import java.util.*;

public class Day3Main {


  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    List<String> wrongItems = new LinkedList<String>();

    for (String rucksackInput : INPUT.split("\n")) {
      Set<String> firstCompartmentItems = new HashSet<String>(
        Arrays.asList(
          rucksackInput.substring(0, rucksackInput.length() / 2).split("")));

      for (String item : rucksackInput.substring(rucksackInput.length() / 2).split("")) {
        if (firstCompartmentItems.contains(item)) {
          wrongItems.add(item);
          break;
        }
      }
    }

    int result = 0;
    for (String s: wrongItems) {
      result += getPriorityForItem(s);
      System.out.println(s + getPriorityForItem(s));
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
