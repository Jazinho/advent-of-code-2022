package com.jpalucki;

public class InputProvider {

  public static String getInput() {
    return INPUT;
  }

  private static final String INPUT = "Monkey 0:\n" +
    "  Starting items: 59, 65, 86, 56, 74, 57, 56\n" +
    "  Operation: new = old * 17\n" +
    "  Test: divisible by 3\n" +
    "    If true: throw to monkey 3\n" +
    "    If false: throw to monkey 6\n" +
    "\n" +
    "Monkey 1:\n" +
    "  Starting items: 63, 83, 50, 63, 56\n" +
    "  Operation: new = old + 2\n" +
    "  Test: divisible by 13\n" +
    "    If true: throw to monkey 3\n" +
    "    If false: throw to monkey 0\n" +
    "\n" +
    "Monkey 2:\n" +
    "  Starting items: 93, 79, 74, 55\n" +
    "  Operation: new = old + 1\n" +
    "  Test: divisible by 2\n" +
    "    If true: throw to monkey 0\n" +
    "    If false: throw to monkey 1\n" +
    "\n" +
    "Monkey 3:\n" +
    "  Starting items: 86, 61, 67, 88, 94, 69, 56, 91\n" +
    "  Operation: new = old + 7\n" +
    "  Test: divisible by 11\n" +
    "    If true: throw to monkey 6\n" +
    "    If false: throw to monkey 7\n" +
    "\n" +
    "Monkey 4:\n" +
    "  Starting items: 76, 50, 51\n" +
    "  Operation: new = old * old\n" +
    "  Test: divisible by 19\n" +
    "    If true: throw to monkey 2\n" +
    "    If false: throw to monkey 5\n" +
    "\n" +
    "Monkey 5:\n" +
    "  Starting items: 77, 76\n" +
    "  Operation: new = old + 8\n" +
    "  Test: divisible by 17\n" +
    "    If true: throw to monkey 2\n" +
    "    If false: throw to monkey 1\n" +
    "\n" +
    "Monkey 6:\n" +
    "  Starting items: 74\n" +
    "  Operation: new = old * 2\n" +
    "  Test: divisible by 5\n" +
    "    If true: throw to monkey 4\n" +
    "    If false: throw to monkey 7\n" +
    "\n" +
    "Monkey 7:\n" +
    "  Starting items: 86, 85, 52, 86, 91, 95\n" +
    "  Operation: new = old + 6\n" +
    "  Test: divisible by 7\n" +
    "    If true: throw to monkey 4\n" +
    "    If false: throw to monkey 5";
}
