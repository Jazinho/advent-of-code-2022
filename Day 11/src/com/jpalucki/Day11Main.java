package com.jpalucki;

import java.util.*;
import java.util.stream.Collectors;

public class Day11Main {

  public static final String ITEMS_LIST_PREFIX = "Starting items: ";
  public static final String OP_ELEMENTS_PREFIX = "Operation: new = old ";

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    ArrayList<Monkey> monkeys = new ArrayList<>();

    String[] monkeysInputs = INPUT.split("\n\n");
    for (String monkeyInput : monkeysInputs) {
      String[] monkeyLines = monkeyInput.split("\n");
      String[] items = monkeyLines[1].substring(2 + ITEMS_LIST_PREFIX.length()).split(",");
      String[] operationElements = monkeyLines[2].substring(2 + OP_ELEMENTS_PREFIX.length()).split(" ");
      String sign = operationElements[0];
      String val = operationElements[1];
      Integer divisibleBy = Integer.valueOf(monkeyLines[3].trim().split(" ")[3]);
      Integer monkeyIf = Integer.valueOf(monkeyLines[4].trim().split(" ")[5]);
      Integer monkeyElse = Integer.valueOf(monkeyLines[5].trim().split(" ")[5]);

      Monkey monkey = new Monkey(
        new LinkedList<>(Arrays.stream(items).map(String::trim).map(Integer::valueOf).collect(Collectors.toList())),
        (item) -> item % divisibleBy == 0 ? monkeyIf : monkeyElse,
        sign.equals("*") ? (item) -> item * getVal(item, val) : (item) -> item + getVal(item, val)
      );
      monkeys.add(monkey);
    }

    for (int i = 1; i <= 20; i++) {
      for (Monkey monkey : monkeys) {
        Integer updatedItem;
        while ((updatedItem = monkey.inspectItem()) != null) {
          int nextMonkeyNo = monkey.getDestMonkeyNo(updatedItem);
          monkeys.get(nextMonkeyNo).addItem(updatedItem);
        }
      }
    }

    for (int i = 0; i < monkeys.size(); i++) {
      System.out.println(i + ": " + monkeys.get(i).getCounter());
    }
  }

  private static Integer getVal(Integer item, String input) {
    return input.equals("old") ? item : Integer.valueOf(input);
  }

}
