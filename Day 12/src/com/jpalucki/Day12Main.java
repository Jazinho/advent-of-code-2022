package com.jpalucki;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12Main {

  public static final String START_MARKER = "S";
  public static final String END_MARKER = "E";
  public static final String z_MARKER = "z";
  public static int mapWidth;
  public static int mapHeigth;
  public static ArrayList<ArrayList<String>> map = new ArrayList<>();

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();

    String[] lines = INPUT.split("\n");
    mapHeigth = lines.length;
    mapWidth = lines[0].length();
    int curX = 0, curY = 0;

    for (int l = 0; l < lines.length; l++) {
      ArrayList<String> letters = Arrays.stream(lines[l].split("")).collect(Collectors.toCollection(ArrayList::new));
      map.add(letters);

      if (lines[l].contains(START_MARKER)) {
        for (int i = 0; i < lines[l].length(); i++) {
          if (letters.get(i).equals(START_MARKER)) {
            curY = l;
            curX = i;
          }
        }
      }
    }

    Integer shortestPath = visit(curX, curY, new HashMap<>(), 0);
    System.out.println(shortestPath);
  }

  private static Integer visit(
    int curX,
    int curY,
    Map<Map.Entry<Integer, Integer>, Integer> visitedPositions,
    int curPathLen
  ) {
    String currentElevation = map.get(curY).get(curX);
    currentElevation = currentElevation.equals(START_MARKER) ? "a" : currentElevation;
    Map.Entry<Integer, Integer> currentPos = Map.entry(curX, curY);
    if (map.get(curY).get(curX).equals(z_MARKER)) {
      return curPathLen + 1;
    }
    if (map.get(curY).get(curX).equals(END_MARKER)) {
      return curPathLen;
    }

    if (visitedPositions.containsKey(currentPos)) {
      Integer bestDistanceSoFar = visitedPositions.get(currentPos);
      if (curPathLen < bestDistanceSoFar) {
        visitedPositions.put(currentPos, curPathLen);
      } else {
        return null;
      }
    } else {
      visitedPositions.put(currentPos, curPathLen);
    }

    Integer leftPathLen = null;
    if (curX > 0 &&
      map.get(curY).get(curX - 1).charAt(0) <= currentElevation.charAt(0) + 1) {
      leftPathLen = visit(curX - 1, curY, visitedPositions, curPathLen + 1);
    }

    Integer rightPathLen = null;
    if (curX < mapWidth - 1 &&
      map.get(curY).get(curX + 1).charAt(0) <= currentElevation.charAt(0) + 1) {
      rightPathLen = visit(curX + 1, curY, visitedPositions, curPathLen + 1);
    }

    // Y DECREASED
    Integer upPathLen = null;
    if (curY > 0 &&
      map.get(curY - 1).get(curX).charAt(0) <= currentElevation.charAt(0) + 1) {
      upPathLen = visit(curX, curY - 1, visitedPositions, curPathLen + 1);
    }

    // Y INCREASED
    Integer downPathLen = null;
    if (curY < mapHeigth - 1 &&
      map.get(curY + 1).get(curX).charAt(0) <= currentElevation.charAt(0) + 1) {
      downPathLen = visit(curX, curY + 1, visitedPositions, curPathLen + 1);
    }

    Optional<Integer> shortest = Stream.of(
      leftPathLen,
      rightPathLen,
      upPathLen,
      downPathLen
    )
    .filter(Objects::nonNull)
    .min(Integer::compare);

    return shortest.orElse(null);
  }
}
