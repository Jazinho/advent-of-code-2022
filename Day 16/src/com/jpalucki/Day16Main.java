package com.jpalucki;

import java.util.*;
import java.util.stream.Collectors;

public class Day16Main {

  private static Integer MINUTES_STARTING_AMOUNT = 30;
  private static String STARTING_POINT = "AA";

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] valvesInfo = INPUT.split("\n");
    Map<String, Valve> valves = new HashMap<>();

    for (String valveInfo: valvesInfo) {
      String[] valveParts = valveInfo.split(" ");
      String valveId = valveParts[1];
      Integer pressureRate = Integer.valueOf(valveParts[4].substring(5, valveParts[4].length() - 1));
      Set<String> pathsTo = new HashSet<>();
      for (int i = 9; i < valveParts.length; i++){
        pathsTo.add(valveParts[i].replace(",", ""));
      }
      Valve valve = new Valve(valveId, pressureRate, pathsTo);
      valves.put(valveId, valve);
    }

    int minutesLeft = MINUTES_STARTING_AMOUNT;
    int currentPressure = 0;

    long secsBefore = System.currentTimeMillis() / 1000;
    int totalPressureReleased = getResultFromVisit(valves, STARTING_POINT, currentPressure, minutesLeft);
    long secsAfter = System.currentTimeMillis() / 1000;

    System.out.println("Total pressure released: " + totalPressureReleased);
    System.out.println("Execution time (sec): " + (secsAfter - secsBefore));
  }

  private static int getResultFromVisit(Map<String, Valve> valves, String currentNode, int currentPressure, int minutesLeft) {
    if (minutesLeft > 0) {
      if (nothingToDo(valves)) {
        return minutesLeft * currentPressure;
      }

      Valve currentValve = valves.get(currentNode);
      int resultFromBestPath = 0;
      int openingBestResult = 0;

      // version without opening also should be considered
      if (!currentValve.isOpen() && currentValve.getRate() > 0) {
        Map<String, Valve> valvesUpdated = valves.entrySet().stream()
          .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().copyOf()));

        int newPressure = currentPressure + currentValve.getRate();
        valvesUpdated.get(currentNode).setOpen(true);
        int minutesLeftAfterOpen =  minutesLeft - 1;
        openingBestResult = currentPressure + getResultFromVisit(valvesUpdated, currentNode, newPressure, minutesLeftAfterOpen);
      }

      for (String pathTo : currentValve.getPathsTo()) {
        int minutesLeftAfterMove =  minutesLeft - 1;
        int resultFromVisit = currentPressure + getResultFromVisit(valves, pathTo, currentPressure, minutesLeftAfterMove);
        if (resultFromVisit > resultFromBestPath) {
          resultFromBestPath = resultFromVisit;
        }
      }

      return Math.max(openingBestResult, resultFromBestPath);
    }

    return 0;
  }

  private static boolean nothingToDo(Map<String, Valve> valves) {
    return valves.values().stream()
      .allMatch(valve -> (valve.isOpen() || valve.getRate().equals(0)));
  }
}
