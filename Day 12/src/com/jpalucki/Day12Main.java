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
    List<Map.Entry<Integer, Integer>> startingPositions = new LinkedList<>();

    for (int l = 0; l < lines.length; l++) {
      ArrayList<String> letters = Arrays.stream(lines[l].split("")).collect(Collectors.toCollection(ArrayList::new));
      map.add(letters);

      for (int i = 0; i < lines[l].length(); i++) {
        if (letters.get(i).equals(START_MARKER) || letters.get(i).equals("a")) {
          startingPositions.add(Map.entry(i, l));
        }
      }
    }

    Map<Map.Entry<Integer, Integer>, Integer> visitedPositions = new HashMap<>();
    Integer shortestPath = startingPositions.stream()
      .map(pos -> visit(pos.getKey(), pos.getValue(), visitedPositions, 0))
      .filter(Objects::nonNull)
      .min(Comparator.comparingInt(e -> e))
      .orElse(null);
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
      isAccessible(curX - 1, curY, map, currentElevation)) {
      leftPathLen = visit(curX - 1, curY, visitedPositions, curPathLen + 1);
    }

    Integer rightPathLen = null;
    if (curX < mapWidth - 1 &&
      isAccessible(curX + 1, curY, map, currentElevation)) {
      rightPathLen = visit(curX + 1, curY, visitedPositions, curPathLen + 1);
    }

    // Y DECREASED
    Integer upPathLen = null;
    if (curY > 0 &&
      isAccessible(curX, curY - 1, map, currentElevation)) {
      upPathLen = visit(curX, curY - 1, visitedPositions, curPathLen + 1);
    }

    // Y INCREASED
    Integer downPathLen = null;
    if (curY < mapHeigth - 1 &&
      isAccessible(curX, curY + 1, map, currentElevation)) {
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

  private static boolean isAccessible(int newX, int newY, ArrayList<ArrayList<String>> map, String currentElevation) {
    String desiredElevation = map.get(newY).get(newX);
    desiredElevation = desiredElevation.equals(END_MARKER) ? "z" : desiredElevation;
    return desiredElevation.charAt(0) <= currentElevation.charAt(0) + 1;
  }
}
