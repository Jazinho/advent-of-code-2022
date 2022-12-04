package com.jpalucki;

public class Day4Main {

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    int counter = 0;

    for (String pairInput: INPUT.split("\n")) {
      String[] elvesInputs = pairInput.split(",");
      String[] elfOneSectors = elvesInputs[0].split("-");
      String[] elfTwoSectors = elvesInputs[1].split("-");
      int elfOneStart = Integer.parseInt(elfOneSectors[0]);
      int elfOneEnd = Integer.parseInt(elfOneSectors[1]);
      int elfTwoStart = Integer.parseInt(elfTwoSectors[0]);
      int elfTwoEnd = Integer.parseInt(elfTwoSectors[1]);
      if (isPartiallyOverlapped(elfOneStart, elfOneEnd, elfTwoStart, elfTwoEnd)) {
        counter++;
        System.out.println(elfOneStart + "-" + elfOneEnd + "   " + elfTwoStart + "-" + elfTwoEnd);
      }
    }

    System.out.println(counter);
  }

  private static boolean isFullyOverlapped(int elfOneStart, int elfOneEnd, int elfTwoStart, int elfTwoEnd) {
    return elfOneStart >= elfTwoStart && elfOneEnd <= elfTwoEnd || elfTwoStart >= elfOneStart && elfTwoEnd <= elfOneEnd;
  }

  private static boolean isPartiallyOverlapped(int elfOneStart, int elfOneEnd, int elfTwoStart, int elfTwoEnd) {
    return elfOneStart >= elfTwoStart && elfTwoEnd >= elfOneStart || elfTwoStart >= elfOneStart && elfOneEnd >= elfTwoStart;
  }

}
