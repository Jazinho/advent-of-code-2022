package com.jpalucki;

public class Main_2 {

  public static short WIN_POINTS = 6;
  public static short DRAW_POINTS = 3;
  public static short DEFEAT_POINTS = 0;

  public static short ROCK_CHOICE_POINTS = 1;
  public static short PAPER_CHOICE_POINTS = 2;
  public static short SCISSOR_CHOICE_POINTS = 3;


  public static void main(String[] args) {
    int totalResult = 0;
    for (String roundInput : InputProvider.getInput().split("\n")) {
      String[] playersInputs = roundInput.split(" ");
      char opponentChoice = playersInputs[0].charAt(0);
      char myChoice = playersInputs[1].charAt(0);
      int roundResult = getRoundResultPoints(opponentChoice, myChoice) + getChoicePoints(myChoice);
      totalResult += roundResult;
    }

    System.out.println(totalResult);
  }

  private static short getRoundResultPoints(char opponentChoice, char myChoice) {
    if (myChoice == getSuperiorChoice(opponentChoice)) return WIN_POINTS;
    if (myChoice == getEqualChoice(opponentChoice)) return DRAW_POINTS;
    return DEFEAT_POINTS;
  }

  private static short getChoicePoints(char choice) {
    switch (choice) {
      case 'X': return ROCK_CHOICE_POINTS;
      case 'Y': return PAPER_CHOICE_POINTS;
      case 'Z': return SCISSOR_CHOICE_POINTS;
      default:
        System.out.println("Invalid choice provided");
        return 0;
    }
  }

  private static short getSuperiorChoice(char choice) {
    switch (choice) {
      case 'A': return 'Y';
      case 'B': return 'Z';
      case 'C': return 'X';
      default:
        System.out.println("Invalid choice provided");
        return '0';
    }
  }

  private static short getEqualChoice(char choice) {
    switch (choice) {
      case 'A': return 'X';
      case 'B': return 'Y';
      case 'C': return 'Z';
      default:
        System.out.println("Invalid choice provided");
        return '0';
    }
  }
}
