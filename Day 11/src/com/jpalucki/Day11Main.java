package com.jpalucki;

import java.math.BigInteger;
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
      BigInteger divisibleBy = BigInteger.valueOf(Long.parseLong(monkeyLines[3].trim().split(" ")[3]));
      Integer monkeyIf = Integer.valueOf(monkeyLines[4].trim().split(" ")[5]);
      Integer monkeyElse = Integer.valueOf(monkeyLines[5].trim().split(" ")[5]);

      Monkey monkey = new Monkey(
        new LinkedList<>(Arrays.stream(items)
          .map(String::trim)
          .map(Long::parseLong)
          .map(BigInteger::valueOf)
          .collect(Collectors.toList())),
        (item) -> item.mod(divisibleBy).intValue() == 0 ? monkeyIf : monkeyElse,
        sign.equals("*") ?
          (item) -> item.multiply(getVal(item, val)) :
          (item) -> item.add(getVal(item, val))
      );
      monkeys.add(monkey);
    }

    for (int i = 1; i <= 1000; i++) {
      System.out.println("iteartion " + i);
      for (Monkey monkey : monkeys) {
        BigInteger updatedItem;
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

  private static BigInteger getVal(BigInteger item, String input) {
    return input.equals("old") ? item : BigInteger.valueOf(Long.parseLong(input));
  }

}
