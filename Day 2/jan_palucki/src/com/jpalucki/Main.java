package com.jpalucki;

public class Main {

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
      char roundResult = playersInputs[1].charAt(0);
      int roundPoints = getChoicePointsByRoundResult(opponentChoice, roundResult) + getResultPoints(roundResult);
      totalResult += roundPoints;
    }

    System.out.println(totalResult);
  }

  private static short getChoicePointsByRoundResult(char opponentChoice, char roundResult) {
    switch (roundResult) {
      case 'X': return getWeakerChoicePoints(opponentChoice);
      case 'Y': return getEqualChoicePoints(opponentChoice);
      case 'Z': return getSuperiorChoicePoints(opponentChoice);
      default:
        System.out.println("Invalid choice provided");
        return 0;
    }
  }

  private static short getResultPoints(char choice) {
    switch (choice) {
      case 'X': return DEFEAT_POINTS;
      case 'Y': return DRAW_POINTS;
      case 'Z': return WIN_POINTS;
      default:
        System.out.println("Invalid choice provided");
        return 0;
    }
  }

  private static short getSuperiorChoicePoints(char choice) {
    switch (choice) {
      case 'A': return PAPER_CHOICE_POINTS;
      case 'B': return SCISSOR_CHOICE_POINTS;
      case 'C': return ROCK_CHOICE_POINTS;
      default:
        System.out.println("Invalid choice provided");
        return '0';
    }
  }

  private static short getEqualChoicePoints(char choice) {
    switch (choice) {
      case 'A': return ROCK_CHOICE_POINTS;
      case 'B': return PAPER_CHOICE_POINTS;
      case 'C': return SCISSOR_CHOICE_POINTS;
      default:
        System.out.println("Invalid choice provided");
        return '0';
    }
  }

  private static short getWeakerChoicePoints(char choice) {
    switch (choice) {
      case 'A': return SCISSOR_CHOICE_POINTS;
      case 'B': return ROCK_CHOICE_POINTS;
      case 'C': return PAPER_CHOICE_POINTS;
      default:
        System.out.println("Invalid choice provided");
        return '0';
    }
  }
}
