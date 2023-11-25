package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Lets the user modify an existing departure.
 */
public class UserModifyTrainDeparture {

  private TrainDepartureRegister trainDepartureRegister;
  private UserInputReader userInputReader;

  // Modify Menu Choices:
  private static final int USER_UPDATE_DEPARTURE_TIME = 1;
  private static final int USER_UPDATE_DEPARTURE_DESTINATION = 2;
  private static final int USER_UPDATE_TRAIN_LINE = 3;
  private static final int USER_ADD_DELAY = 4;
  private static final int USER_ADD_TRACK = 5;

  /**
   * Creates an instance of UserEditTrainDeparture.
   *
   * @param trainDepartureRegister trainDepartureRegister
   */
  public UserModifyTrainDeparture(TrainDepartureRegister trainDepartureRegister) {
    this.trainDepartureRegister = trainDepartureRegister;
    this.userInputReader = new UserInputReader();
  }

  /**
   * Lets the user add a delay to an existing departure.
   * If it is not written on the format "hh:mm" or no train departure is found with the given train
   * number the user will be notified accordingly.
   *
   * @param departureToModify The departure to modify
   */
  private void userAddDelay(TrainDeparture departureToModify) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    Pattern pattern = Pattern.compile(patternExample);

    if (departureToModify != null) {
      System.out.println("Enter the amount delayed on the format hh:mm");
      String departureTime = userInputReader.getUserString();

      while (!pattern.matcher(departureTime).matches()) {
        System.out.println(TrainDispatchAppUi.RED + "Please make sure the delay "
            + "time is written in correct hh:mm format");
        System.out.println("You entered: " + TrainDispatchAppUi.COLOR_RESET + departureTime);
        System.out.println();
        System.out.println("Please enter a new delay");
        departureTime = userInputReader.getUserString();
      }
      departureToModify.setDelayTime(departureTime);
      System.out.println(TrainDispatchAppUi.GREEN + "Delay updated"
          + TrainDispatchAppUi.COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user update departure time of an existing departure.
   * If it is not written on the format "hh:mm" or no train departure is found with the given train
   * number the user will be notified accordingly.
   *
   * @param departureToModify The departure to modify
   */
  private void userUpdateDepartureTime(TrainDeparture departureToModify) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    Pattern pattern = Pattern.compile(patternExample);

    if (departureToModify != null) {
      System.out.println("Enter the new departure time on the format hh:mm");
      String departureTime = userInputReader.getUserString();

      while (!pattern.matcher(departureTime).matches()) {
        System.out.println(TrainDispatchAppUi.RED + "Please make sure the departure "
            + "time is written in correct hh:mm format");
        System.out.println("You entered: " + TrainDispatchAppUi.COLOR_RESET + departureTime);
        System.out.println();
        System.out.println("Please enter a new departure time");
        departureTime = userInputReader.getUserString();
      }
      departureToModify.setDepartureTime(departureTime);
      System.out.println(TrainDispatchAppUi.GREEN + "Delay updated"
          + TrainDispatchAppUi.COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user add a track number to an existing departure.
   * Track number must be between 1 and 99, if it isn't the user will be notified accordingly.
   */
  private void userUpdateTrack(TrainDeparture trainDeparture) {
    TrainDeparture departure = trainDeparture;
    if (departure != null) {
      System.out.println("Enter new track number");
      int trackNum = userInputReader.getUserInt();
      while (trackNum > 99 || trackNum < 1) {
        System.out.println(TrainDispatchAppUi.RED + "Track number must be between 1 and 99"
            + TrainDispatchAppUi.COLOR_RESET);
        System.out.println("Enter new track number");
        trackNum = userInputReader.getUserInt();
      }
      departure.setTrackNumber(trackNum);
      System.out.println(TrainDispatchAppUi.GREEN + "Track updated"
          + TrainDispatchAppUi.COLOR_RESET);
    }
    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user update the destination of an existing departure.
   *
   * @param departureToModify The departure to modify.
   */
  private void userUpdateDepartureDestination(TrainDeparture departureToModify) {
    if (departureToModify != null) {
      System.out.println("Enter new destination");
      departureToModify.setDestination(userInputReader.getUserString());
      System.out.println(TrainDispatchAppUi.GREEN + "Destination updated"
          + TrainDispatchAppUi.COLOR_RESET);
    }
    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user update train line of an existing departure.
   *
   * @param departureToModify The departure to modify.
   */
  private void userUpdateTrainLine(TrainDeparture departureToModify) {
    if (departureToModify != null) {
      System.out.println("Enter new train line");
      departureToModify.setTrainLine(userInputReader.getUserString());
      System.out.println(TrainDispatchAppUi.GREEN + "Train line updated"
          + TrainDispatchAppUi.COLOR_RESET);
    }
    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user select a train departure.
   * If no departure is found null is returned.
   *
   * @return selectedDeparture The selected departure
   */
  public TrainDeparture userSelectDeparture() {
    System.out.println("Enter the train number of the departure you would like to modify");
    TrainDeparture selectedDeparture =
        trainDepartureRegister.getTrainDepartureByTrainNumber(userInputReader.getUserInt());
    if (selectedDeparture != null) {
      System.out.println(TrainDispatchAppUi.GREEN + "Departure selected"
          + TrainDispatchAppUi.COLOR_RESET);
      System.out.println();
    } else {
      System.out.println(TrainDispatchAppUi.RED + "Departure not found"
          + TrainDispatchAppUi.COLOR_RESET);
      System.out.println();
      System.out.println("Press enter to return to main menu");
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
    }

    return selectedDeparture;
  }

  /**
   * Prints modify menu with choices.
   */
  public void printModifyMenu() {
    System.out.println("Select one of the following choices");
    System.out.println("1. Update departure time");
    System.out.println("2. Update departure Destination");
    System.out.println("3. Update train line");
    System.out.println("4. Add/Update Delay");
    System.out.println("5. Add/Update Track");
    System.out.println("9. Return to main menu");
  }

  /**
   * Executes the selected menu choice by the user.
   *
   * @param selectedChoice Selected choice by the user
   * @param departureToModify Departure to modify
   */
  public void executeModifyMenuChoice(int selectedChoice, TrainDeparture departureToModify) {

    switch (selectedChoice) {
      case USER_UPDATE_DEPARTURE_TIME:
        userUpdateDepartureTime(departureToModify);
        break;

      case USER_UPDATE_DEPARTURE_DESTINATION:
        userUpdateDepartureDestination(departureToModify);
        break;

      case USER_UPDATE_TRAIN_LINE:
        userUpdateTrainLine(departureToModify);
        break;

      case USER_ADD_DELAY:
        userAddDelay(departureToModify);
        break;

      case USER_ADD_TRACK:
        userUpdateTrack(departureToModify);
        break;

      default:
    }
  }
}
