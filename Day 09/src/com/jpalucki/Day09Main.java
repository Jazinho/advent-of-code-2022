package com.jpalucki;

import java.util.*;

public class Day09Main {

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] rows = INPUT.split("\n");
    LinkedList<Map.Entry<Integer, Integer>> nodes = new LinkedList<>();
    for (int i = 1; i <= 10; i++) {
      nodes.push(Map.entry(0, 0));
    }
    Set<Map.Entry<Integer, Integer>> visitedPositions = new HashSet<>();
    visitedPositions.add(Map.entry(0, 0));

    for (String move : rows) {
      String direction = move.split(" ")[0];
      Integer stepLength = Integer.valueOf(move.split(" ")[1]);

      for (int i = 1; i <= stepLength; i++) {
        for (int nodeNo = 0; nodeNo < 10; nodeNo++) {
          Map.Entry<Integer, Integer> newNodePosition = null;
          if (nodeNo == 0) {
            newNodePosition = getNewHeadPosition(nodes.get(nodeNo), direction);
            nodes.set(nodeNo, newNodePosition);
          } else {
            Map.Entry<Integer, Integer> previousNode = nodes.get(nodeNo - 1);
            Map.Entry<Integer, Integer> currentNode = nodes.get(nodeNo);
            newNodePosition = currentNode;

            if (!isAdjacent(currentNode, previousNode)) {
              newNodePosition = getNewNodePosition(currentNode, previousNode);
              nodes.set(nodeNo, newNodePosition);
              if (nodeNo == 9) {
                visitedPositions.add(newNodePosition);
              }
            }
          }

          if (newNodePosition == null) {
            return;
          }
        }
      }
    }

    System.out.println(visitedPositions);
    System.out.println(visitedPositions.size());
  }

  private static Map.Entry<Integer, Integer> getNewHeadPosition(
    Map.Entry<Integer, Integer> headPosition,
    String direction
  ) {
    switch (direction) {
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

  private static Map.Entry<Integer, Integer> getNewNodePosition(
    Map.Entry<Integer, Integer> currentNodePosition,
    Map.Entry<Integer, Integer> previousNodePosition
  ) {
    Integer nodeX = currentNodePosition.getKey();
    Integer nodeY = currentNodePosition.getValue();
    Integer previousNodeX = previousNodePosition.getKey();
    Integer previousNodeY = previousNodePosition.getValue();
    Integer diffX = previousNodeX - nodeX;
    Integer diffY = previousNodeY - nodeY;

    return Map.entry(nodeX + Integer.signum(diffX), nodeY + Integer.signum(diffY));
  }

  private static boolean isAdjacent(
    Map.Entry<Integer, Integer> currentNodePosition,
    Map.Entry<Integer, Integer> previousNodePosition
  ) {
    Integer headX = previousNodePosition.getKey();
    Integer headY = previousNodePosition.getValue();
    Integer tailX = currentNodePosition.getKey();
    Integer tailY = currentNodePosition.getValue();
    return Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1;
  }
}
