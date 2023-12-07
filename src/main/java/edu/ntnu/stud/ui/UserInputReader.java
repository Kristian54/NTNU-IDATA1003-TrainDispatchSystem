package edu.ntnu.stud.ui;

import java.util.Scanner;

/**
 * Represents the user input reader.
 *
 * <p>The following functionality is implemented:
 * <ul>
 *   <li>Take input int from user and return it as int</li>
 *   <li>Take input string from user and return it as string</li>
 * </ul>
 */
public class UserInputReader {

  public UserInputReader() {
  }

  /**
   * Gets a string from the user.
   * If no input is given, an empty string will be returned
   *
   * @return inputString user´s string. If empty an empty string will be returned.
   */
  public String getUserString() {
    String inputString = "";

    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNext()) {
      inputString = userInput.next();
    }
    return inputString;
  }

  /**
   * Gets an int from the user.
   * If the user enters an invalid int -1 will be returned.
   *
   * @return user´s int. If invalid -1 is returned
   */
  public int getUserInt() {
    int inputInt = -1;

    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNextInt()) {
      inputInt = userInput.nextInt();
    }
    return inputInt;
  }
}
