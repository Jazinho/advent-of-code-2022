package com.jpalucki;

import java.util.*;

public class Day09Main {

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] rows = INPUT.split("\n");
    Map.Entry<Integer, Integer> headPosition = Map.entry(0, 0);
    Map.Entry<Integer, Integer> tailPosition = Map.entry(0, 0);
    Set<Map.Entry<Integer, Integer>> visitedPositions = new HashSet<>();
    visitedPositions.add(tailPosition);

    for (String move : rows) {
      String direction = move.split(" ")[0];
      Integer stepLength = Integer.valueOf(move.split(" ")[1]);

      for (int i = 1; i <= stepLength; i++) {
        Map.Entry<Integer, Integer> newHeadPosition = getNewHeadPosition(headPosition, direction);
        if (newHeadPosition == null) return;
        if (!isAdjacent(newHeadPosition, tailPosition)) {
          tailPosition = headPosition;
          visitedPositions.add(tailPosition);
        }
        headPosition = newHeadPosition;
      }
    }

//    System.out.println(visitedPositions);
    System.out.println(visitedPositions.size());
  }

  private static Map.Entry<Integer, Integer> getNewHeadPosition(Map.Entry<Integer, Integer> headPosition, String direction) {
    switch (direction){
      case "U": {
        return Map.entry(headPosition.getKey(), headPosition.getValue() + 1);
      }
      case "D": {
        return Map.entry(headPosition.getKey(), headPosition.getValue() - 1);
      }
      case "L": {
        return Map.entry(headPosition.getKey() - 1, headPosition.getValue());
      }
      case "R": {
        return Map.entry(headPosition.getKey() + 1, headPosition.getValue());
      }
      default:
        return null;
    }
  }

  private static Map.Entry<Integer, Integer> getNewTailPosition(Map.Entry<Integer, Integer> headPosition, String direction) {
    switch (direction){
      case "U": {
        return Map.entry(headPosition.getKey(), headPosition.getKey() + 1);
      }
      case "D": {
        return Map.entry(headPosition.getKey(), headPosition.getKey() - 1);
      }
      case "L": {
        return Map.entry(headPosition.getKey() - 1, headPosition.getKey());
      }
      case "R": {
        return Map.entry(headPosition.getKey() + 1, headPosition.getKey());
      }
      default:
        return null;
    }
  }

  private static boolean isAdjacent(Map.Entry<Integer, Integer> headPosition, Map.Entry<Integer, Integer> tailPosition) {
    Integer headX = headPosition.getKey();
    Integer headY = headPosition.getValue();
    Integer tailX = tailPosition.getKey();
    Integer tailY = tailPosition.getValue();
    return Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1;
  }
}
