package com.jpalucki;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12Main {

  public static final String START_MARKER = "S";
  public static final String END_MARKER = "z";
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

    List<Map.Entry<Integer, Integer>> result = visit(curX, curY, new HashSet<>(), new LinkedList<>());
    System.out.println(result.size());
  }

  private static List<Map.Entry<Integer, Integer>> visit(
    int curX,
    int curY,
    Set<Map.Entry<Integer, Integer>> visitedPositions,
    LinkedList<Map.Entry<Integer, Integer>> currentPath
  ) {
    String currentElevation = map.get(curY).get(curX);
    currentElevation = currentElevation.equals(START_MARKER) ? "a" : currentElevation;
    Map.Entry<Integer, Integer> currentPos = Map.entry(curX, curY);
    if (map.get(curY).get(curX).equals(END_MARKER)) {
      currentPath.add(currentPos);
      return currentPath;
    }

    visitedPositions = new HashSet<>(visitedPositions);
    visitedPositions.add(currentPos);
    currentPath = new LinkedList<>(currentPath);
    currentPath.add(currentPos);
    List<Map.Entry<Integer, Integer>> leftPath = null;
    if (curX > 0 && !visitedPositions.contains(Map.entry(curX - 1, curY)) &&
      map.get(curY).get(curX - 1).codePointAt(0) <= currentElevation.codePointAt(0) + 1) {
      leftPath = visit(curX - 1, curY, visitedPositions, currentPath);
    }

    List<Map.Entry<Integer, Integer>> rightPath = null;
    if (curX < mapWidth - 1 && !visitedPositions.contains(Map.entry(curX + 1, curY)) &&
      map.get(curY).get(curX + 1).codePointAt(0) <= currentElevation.codePointAt(0) + 1) {
      rightPath = visit(curX + 1, curY, visitedPositions, currentPath);
    }

    // Y DECREASED
    List<Map.Entry<Integer, Integer>> upPath = null;
    if (curY > 0 && !visitedPositions.contains(Map.entry(curX, curY - 1)) &&
      map.get(curY - 1).get(curX).codePointAt(0) <= currentElevation.codePointAt(0) + 1) {
      upPath = visit(curX, curY - 1, visitedPositions, currentPath);
    }

    // Y INCREASED
    List<Map.Entry<Integer, Integer>> downPath = null;
    if (curY < mapHeigth - 1 && !visitedPositions.contains(Map.entry(curX, curY + 1)) &&
      map.get(curY + 1).get(curX).codePointAt(0) <= currentElevation.codePointAt(0) + 1) {
      downPath = visit(curX, curY + 1, visitedPositions, currentPath);
    }

    Optional<List<Map.Entry<Integer, Integer>>> shortest = Stream.of(leftPath, rightPath, upPath, downPath)
      .filter(Objects::nonNull)
      .min(Comparator.comparingInt(List::size));

    return shortest.orElse(null);
  }
}
