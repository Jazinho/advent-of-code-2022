package com.jpalucki;

import java.util.*;

public class Day05Main {

  private final static String MOVE_COMMAND_PREFIX = "move";

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    LinkedList<String> stack1 = getAsList("WMLF");
    LinkedList<String> stack2 = getAsList("BZVMF");
    LinkedList<String> stack3 = getAsList("HVRSLQ");
    LinkedList<String> stack4 = getAsList("FSVQPMTJ");
    LinkedList<String> stack5 = getAsList("LSW");
    LinkedList<String> stack6 = getAsList("FVPMRJW");
    LinkedList<String> stack7 = getAsList("JQCPNRF");
    LinkedList<String> stack8 = getAsList("VHPSZWRB");
    LinkedList<String> stack9 = getAsList("BMJCGHZW");
    List<LinkedList<String>> stacks = Arrays.asList(
      stack1,
      stack2,
      stack3,
      stack4,
      stack5,
      stack6,
      stack7,
      stack8,
      stack9
    );

    for (String command : INPUT.split("\n")) {
      if (command.startsWith(MOVE_COMMAND_PREFIX)) {
        String[] commandParts = command.split(" ");
        int itemsCount = Integer.parseInt(commandParts[1]);
        int fromStackNo = Integer.parseInt(commandParts[3]);
        int toStackNo = Integer.parseInt(commandParts[5]);
        moveAsMover9001(stacks, fromStackNo, toStackNo, itemsCount);
      }
    }

    System.out.println(stacks);
  }

  private static LinkedList<String> getAsList(String stackInput) {
    return new LinkedList<String>(Arrays.asList(stackInput.split("")));
  }

  /**
   * Performs moving elements as described for CrateMover 9000 machine model (moves elements one by one, even if the
   * multiple elements are to be moved from the same starting point and to the same end
   */
  private static void moveAsMover9000(
    List<LinkedList<String>> stacks,
    int fromStackNo,
    int toStackNo,
    int itemsCounts
  ) {
    LinkedList<String> fromStack = stacks.get(fromStackNo - 1);
    LinkedList<String> toStack = stacks.get(toStackNo - 1);
    if (itemsCounts > fromStack.size()) {
      System.out.println("ItemsCount " + itemsCounts + " exceeding fromStack nr." + fromStackNo + " size");
    }

    for (int i = 1; i <= itemsCounts; i++) {
      toStack.add(fromStack.removeLast());
    }
  }

  /**
   * Performs moving elements as described for CrateMover 9001 machine model (moves multiple elements if the
   * elements are to be moved from the same starting point and to the same end
   */
  private static void moveAsMover9001(
    List<LinkedList<String>> stacks,
    int fromStackNo,
    int toStackNo,
    int itemsCounts
  ) {
    LinkedList<String> fromStack = stacks.get(fromStackNo - 1);
    LinkedList<String> toStack = stacks.get(toStackNo - 1);
    if (itemsCounts > fromStack.size()) {
      System.out.println("ItemsCount " + itemsCounts + " exceeding fromStack nr." + fromStackNo + " size");
    }

    for (int i = 1; i <= itemsCounts; i++) {
      toStack.add(fromStack.remove(fromStack.size() - 1 - itemsCounts + i));
    }
  }
}
