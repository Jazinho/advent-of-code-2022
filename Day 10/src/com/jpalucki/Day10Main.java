package com.jpalucki;

import java.util.*;

public class Day10Main {

  static final String ADDX_COMMAND = "addx";
  static final int ADD_COMMAND_CYCLES = 2;
  static Set<Integer> DESIRED_CYCLES_SET = new HashSet<>(List.of(20, 60, 100, 140, 180, 220));

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] rows = INPUT.split("\n");
    int finalResult = 0;
    int currentRegisterValue = 1;
    int currentCommandNo = 0;

    for (int i = 1; i <= 220; i++) {
      String command = rows[currentCommandNo];

      if (command.startsWith(ADDX_COMMAND)) {
        int nextAddedValue = Integer.parseInt(command.split(" ")[1]);
        for (int j = 1; j < ADD_COMMAND_CYCLES; j++) {
          finalResult = checkCycle(i, currentRegisterValue, finalResult);
          i++;
        }

        finalResult = checkCycle(i, currentRegisterValue, finalResult);
        currentRegisterValue += nextAddedValue;
      } else {
        finalResult = checkCycle(i, currentRegisterValue, finalResult);
      }

      currentCommandNo++;
    }

    System.out.println("Sum of cycles is: " + finalResult);
  }

  private static int checkCycle(int cycleNo, int currentRegisterValue, int currentResult) {
    if (DESIRED_CYCLES_SET.contains(cycleNo)) {
      System.out.println(currentResult + " + (" + cycleNo + " * " + currentRegisterValue + ")");
      return currentResult + (cycleNo * currentRegisterValue);
    } else {
      return currentResult;
    }
  }
}
