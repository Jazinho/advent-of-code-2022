package com.jpalucki;

import java.util.*;

public class Day08Main {

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] rows = INPUT.split("\n");
    LinkedList<ArrayList<String>> rowsList = new LinkedList<>();
    for (int i = 0; i < rows.length; i++) {
      ArrayList<String> row = new ArrayList<>(Arrays.asList(rows[i].split("")));
      rowsList.add(row);
    }

    ArrayList<ArrayList<String>> grid = new ArrayList<>(rowsList);

    if (grid.size() <= 4) {
      return;
    }

    // Assuming square grid
    int maxVisibilityScore = 0;
    for (int r = 1; r < grid.size() - 1; r++) {
      for (int c = 1; c < grid.get(r).size() - 1; c++) {
        int visibilityScore = getVisibilityScore(grid, r, c);
        if (visibilityScore > maxVisibilityScore) {
//          System.out.println("Reached for " + r + "," + c);
          maxVisibilityScore = visibilityScore;
        }
      }
    }

    System.out.println("Max Visible Score: " + maxVisibilityScore);
  }

  /**
   * @param grid - trees grid
   * @param r    - row number (index)
   * @param c    - column number (index)
   *
   * @return visibility score which is multiplication of trees number that are visible in each direction
   */
  private static int getVisibilityScore(ArrayList<ArrayList<String>> grid, int r, int c) {
    int up = isVisibleUp(grid, r, c, 1);
    int down = isVisibleDown(grid, r, c, 1);
    int right = isVisibleRight(grid, r, c, 1);
    int left = isVisibleLeft(grid, r, c, 1);
    return up * down * left * right;
  }

  private static int isVisibleLeft(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (c - i >= 0) {
      int currentHeight = Integer.parseInt(grid.get(r).get(c - i));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      if (currentHeight < checkedHeight) {
        return isVisibleLeft(grid, r, c, i + 1);
      }
    } else {
      return i - 1;
    }
    return i;
  }

  private static int isVisibleRight(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (c + i < grid.get(r).size()) {
      int currentHeight = Integer.parseInt(grid.get(r).get(c + i));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      if (currentHeight < checkedHeight) {
        return isVisibleRight(grid, r, c, i + 1);
      }
    } else {
      return i - 1;
    }
    return i;
  }

  private static int isVisibleUp(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (r - i >= 0) {
      int currentHeight = Integer.parseInt(grid.get(r - i).get(c));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      if (currentHeight < checkedHeight) {
        return isVisibleUp(grid, r, c, i + 1);
      }
    } else {
      return i - 1;
    }
    return i;
  }

  private static int isVisibleDown(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (r + i < grid.size()) {
      int currentHeight = Integer.parseInt(grid.get(r + i).get(c));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      if (currentHeight < checkedHeight) {
        return isVisibleDown(grid, r, c, i + 1);
      }
    } else {
      return i - 1;
    }
    return i;
  }
}
