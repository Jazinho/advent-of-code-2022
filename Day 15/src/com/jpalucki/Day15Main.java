package com.jpalucki;

import java.util.*;
import java.util.stream.Collectors;

public class Day15Main {

  private static Integer CHECKED_ROW_NO = 2000000;

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();

    String[] sensorOutput = INPUT.split("\n");
    Map<Map.Entry<Integer, Integer>, Map.Entry<Integer, Integer>> sensorsInfo = new HashMap<>();
    Set<Integer> scannedIndexes = new HashSet<>();

    for (int l = 0; l < sensorOutput.length; l++) {
      String[] outputParts = sensorOutput[l].split(" ");
      int sensorX = Integer.parseInt(outputParts[2].substring(2, outputParts[2].length() - 1));
      int sensorY = Integer.parseInt(outputParts[3].substring(2, outputParts[3].length() - 1));
      int beaconX = Integer.parseInt(outputParts[8].substring(2, outputParts[8].length() - 1));
      int beaconY = Integer.parseInt(outputParts[9].substring(2));
      sensorsInfo.put(Map.entry(sensorX, sensorY), Map.entry(beaconX, beaconY));
    }

    int iterationCount = 4_000_000;
    for (int i = 0; i < iterationCount; i++) {
      sensorsScan(sensorsInfo, i, iterationCount);
    }
    Set<Integer> sensorsOnLine = sensorsInfo.keySet().stream()
      .filter(sensor -> sensor.getValue().equals(CHECKED_ROW_NO))
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
    Set<Integer> beaconsOnLine = sensorsInfo.values().stream()
      .filter(beacon -> beacon.getValue().equals(CHECKED_ROW_NO))
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());

    System.out.println(sensorsOnLine.size() + " sensors on line");
    System.out.println(beaconsOnLine.size() + " beacons on line");
    int result = scannedIndexes.size() - beaconsOnLine.size() - sensorsOnLine.size();
    System.out.println(result + " places cannot contain final position");
  }

  private static void sensorsScan(
    Map<Map.Entry<Integer, Integer>, Map.Entry<Integer, Integer>> sensors,
    int lineNo,
    int checkingLimit
  ) {
    Set<Map.Entry<Integer, Integer>> ranges = new HashSet<>();
    for (Map.Entry<Map.Entry<Integer, Integer>, Map.Entry<Integer, Integer>> sensorInfo : sensors.entrySet()) {
      int sensorX = sensorInfo.getKey().getKey();
      int sensorY = sensorInfo.getKey().getValue();
      int beaconX = sensorInfo.getValue().getKey();
      int beaconY = sensorInfo.getValue().getValue();
      int radarRange = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);
      int relatedPointDistance = Math.abs(lineNo - sensorY);
      int maxValue = radarRange - relatedPointDistance;

      if (maxValue >= 0) {
        int minX = sensorX - maxValue;
        int maxX = sensorX + maxValue;
        ranges.add(Map.entry(minX, maxX));
      }
    }

    List<Map.Entry<Integer, Integer>> orderedList = ranges.stream()
      .sorted(Comparator.comparingInt(Map.Entry::getKey))
      .collect(Collectors.toList());
    int curMax = 0;
    int curMin = checkingLimit;
    for (int i = 0; i < orderedList.size() - 1; i++) {
      int rangeMin = orderedList.get(i).getKey();
      int rangeMax = orderedList.get(i).getValue();
      if (rangeMin < curMin) {
        curMin = rangeMin;
      }
      if (curMax < rangeMax) {
        curMax = rangeMax;
      }

      int nextRangeMin = orderedList.get(i + 1).getKey();
      if (nextRangeMin > curMax) {
        System.out.println("line " + lineNo + " is a candidate");
      }
    }
  }
}
