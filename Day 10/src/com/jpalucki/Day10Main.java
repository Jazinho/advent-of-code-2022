package com.jpalucki;

public class Day10Main {

  static final String ADDX_COMMAND = "addx";
  static final int ADD_COMMAND_CYCLES = 2;
  static final int SINGLE_ROW_PIXELS_COUNT = 40;

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] rows = INPUT.split("\n");
    int spritePos = 1;
    int currentCommandNo = 0;
    for (int crtPos = 1; true; crtPos++) {
      String command = rows[currentCommandNo];

      if (command.startsWith(ADDX_COMMAND)) {
        int nextAddedValue = Integer.parseInt(command.split(" ")[1]);
        for (int j = 1; j < ADD_COMMAND_CYCLES; j++) {
          drawPixel(crtPos, spritePos);
          crtPos++;
        }

        drawPixel(crtPos, spritePos);
        spritePos += nextAddedValue;
      } else {
        drawPixel(crtPos, spritePos);
      }

      currentCommandNo++;
      if (currentCommandNo == rows.length) {
        return;
      }
    }
  }

  private static boolean isRowFinalPixel(int cycleNo) {
    return cycleNo % SINGLE_ROW_PIXELS_COUNT == 0;
  }

  private static void drawPixel(int crtPos, int spritePos) {
    crtPos = crtPos % SINGLE_ROW_PIXELS_COUNT;
    if (crtPos - spritePos >= 0 && crtPos - spritePos <= 2) {
      System.out.print("#");
    } else {
      System.out.print(".");
    }

    if (isRowFinalPixel(crtPos)) {
      System.out.println();
    }
  }
}
