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
    int visibleTreesCounter = (grid.size() - 1) * 4;
    for (int r = 1; r < grid.size() - 1; r++) {
      for (int c = 1; c < grid.get(r).size() - 1; c++) {
        boolean isVisible = isVisible(grid, r, c);
        if (isVisible) {
//          System.out.println("Tree [" + r + "," + c + "] is visible");
          visibleTreesCounter++;
        }
      }
    }

    System.out.println("Visible Trees Count: " + visibleTreesCounter);
  }

  /**
   * @param grid - trees grid
   * @param r    - row number (index)
   * @param c    - column number (index)
   *
   * @return boolean saying if tree is visible or not
   */
  private static boolean isVisible(ArrayList<ArrayList<String>> grid, int r, int c) {
    return isVisibleUp(grid, r, c, 1) ||
      isVisibleDown(grid, r, c, 1) ||
      isVisibleRight(grid, r, c, 1) ||
      isVisibleLeft(grid, r, c, 1);
  }

  private static boolean isVisibleLeft(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (c - i >= 0) {
      int currentHeight = Integer.parseInt(grid.get(r).get(c-i));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      return currentHeight < checkedHeight && isVisibleLeft(grid, r, c, i + 1);
    }
    return true;
  }

  private static boolean isVisibleRight(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (c + i < grid.get(r).size()) {
      int currentHeight = Integer.parseInt(grid.get(r).get(c+i));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      return currentHeight < checkedHeight && isVisibleRight(grid, r, c, i + 1);
    }
    return true;
  }

  private static boolean isVisibleUp(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (r - i >= 0) {
      int currentHeight = Integer.parseInt(grid.get(r-i).get(c));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      return currentHeight < checkedHeight && isVisibleUp(grid, r, c, i + 1);
    }
    return true;
  }

  private static boolean isVisibleDown(ArrayList<ArrayList<String>> grid, int r, int c, int i) {
    if (r + i < grid.size()) {
      int currentHeight = Integer.parseInt(grid.get(r+i).get(c));
      int checkedHeight = Integer.parseInt(grid.get(r).get(c));
      return currentHeight < checkedHeight && isVisibleDown(grid, r, c, i + 1);
    }
    return true;
  }
}
