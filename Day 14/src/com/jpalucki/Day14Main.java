package com.jpalucki;

import java.util.*;

public class Day14Main {

  private static Integer STARTING_POINT_POS = 500;
  private static String ROCK_MARKER = "#";

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();

    String[] rockStructures = INPUT.split("\n");
    int minX = STARTING_POINT_POS, maxX = STARTING_POINT_POS;
    int minY = 1000, maxY = 0;
    List<List<Map.Entry<Integer, Integer>>> rocks = new LinkedList<>();

    for (int l = 0; l < rockStructures.length; l++) {
      String[] rockPoints = rockStructures[l].split(" -> ");
      List<Map.Entry<Integer, Integer>> singleRock = new LinkedList<>();
      for (int r = 0; r < rockPoints.length; r++) {
        String[] coords = rockPoints[r].split(",");
        singleRock.add(Map.entry(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));

        if (Integer.parseInt(coords[0]) < minX) {
          minX = Integer.parseInt(coords[0]);
        }

        if (Integer.parseInt(coords[0]) > maxX) {
          maxX = Integer.parseInt(coords[0]);
        }

        if (Integer.parseInt(coords[1]) > maxY) {
          maxY = Integer.parseInt(coords[1]);
        }

        if (Integer.parseInt(coords[1]) < minY) {
          minY = Integer.parseInt(coords[1]);
        }
      }
      rocks.add(singleRock);
    }

    minX = minX - 1;
    int mapWidth = maxX - minX + 1;
    int mapHeigth = maxY + 1;
    String[][] map = new String[mapHeigth][mapWidth];
    for (int i = 0; i < minY; i++){
      for (int j = 0; j < mapWidth; j++) {
        map[i][j] = ".";
      }
    }
    initRocks(map, rocks, minX);
    drawMap(map);
    simulateSand(map, minX, maxY);
  }

  private static void initRocks(String[][] map, List<List<Map.Entry<Integer, Integer>>> rocks, int mapShiftOnX) {
    for(List<Map.Entry<Integer, Integer>> rock: rocks) {
      for(int el = 0; el < rock.size() - 1; el++){
        Map.Entry<Integer, Integer> curPoint = rock.get(el);
        Map.Entry<Integer, Integer> nextPoint = rock.get(el + 1);
        if (curPoint.getKey().equals(nextPoint.getKey())) {
          int size = Math.abs(curPoint.getValue() - nextPoint.getValue()) + 1;
          int lower = curPoint.getValue() < nextPoint.getValue() ? curPoint.getValue() : nextPoint.getValue();
          for (int i = 0; i < size; i++){
            map[lower + i][curPoint.getKey() - mapShiftOnX] = ROCK_MARKER;
          }
        } else {
          int size = Math.abs(curPoint.getKey() - nextPoint.getKey()) + 1;
          int lower = curPoint.getKey() < nextPoint.getKey() ? curPoint.getKey() : nextPoint.getKey();
          for (int i = 0; i < size; i++){
            map[curPoint.getValue()][lower + i - mapShiftOnX] = ROCK_MARKER;
          }
        }
      }
    }
  }

  private static void drawMap(String[][] map){
    for (String[] row: map) {
      for (String cell: row){
        System.out.print(cell == null ? "." : cell);
      }
      System.out.println();
    }
  }

  private static void simulateSand(String[][] map, int mapShiftOnX, int maxY){
    int startingPositionX = STARTING_POINT_POS - mapShiftOnX;
    int sandCounter = 0;
    boolean sandOverflow = false;

    while (!sandOverflow) {
      int sandCurPosX = startingPositionX, sandCurPosY = 0;

      while (true) {
        if (sandCurPosY >= maxY) {
          sandOverflow = true;
          break;
        }

        if (map[sandCurPosY+1][sandCurPosX] == null || map[sandCurPosY+1][sandCurPosX].equals(".")){
          sandCurPosY++;
        } else if (map[sandCurPosY+1][sandCurPosX-1] == null || map[sandCurPosY+1][sandCurPosX-1].equals(".")){
          sandCurPosY++;
          sandCurPosX--;
        } else if (map[sandCurPosY+1][sandCurPosX+1] == null || map[sandCurPosY+1][sandCurPosX+1].equals(".")){
          sandCurPosY++;
          sandCurPosX++;
        } else {
          map[sandCurPosY][sandCurPosX] = "o";
          break;
        }
      }

      sandCounter++;
    }

    System.out.println("Sand no. was overflow: " + sandCounter);
  }
}
