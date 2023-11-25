package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Lets the user add or remove a departure to/from the register.
 */
public class UserAddRemoveTrainDeparture {
  private TrainDepartureRegister trainDepartureRegister;
  private UserInputReader userInputReader;

  /**
   * Creates an instance of UserAddRemoveTrainDeparture.
   *
   * @param trainDepartureRegister trainDepartureRegister
   */
  public UserAddRemoveTrainDeparture(TrainDepartureRegister trainDepartureRegister) {
    this.trainDepartureRegister = trainDepartureRegister;
    this.userInputReader = new UserInputReader();
  }

  /**
   * Lets the user remove a departure by selecting it´s train number.
   * If no departure is found with the given train number it will notify the user of that and
   * nothing will be removed.
   */
  public void userRemoveDeparture() {
    System.out.println("Enter the train number of the departure you would like to remove:");
    int trainNumber = userInputReader.getUserInt();

    TrainDeparture departureToRemove =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    boolean trainDepartureRemoved = trainDepartureRegister.removeDeparture(departureToRemove);
    if (trainDepartureRemoved) {
      System.out.println(TrainDispatchAppUi.GREEN + "Train departure removed"
          + TrainDispatchAppUi.COLOR_RESET);
    } else {
      System.out.println(TrainDispatchAppUi.RED + "Train departure not found");
      System.out.println("You entered: " + TrainDispatchAppUi.COLOR_RESET + trainNumber);
    }

    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  /**
   * Lets the user add a train departure.
   * Train number must be unique and between 0 and 100. If it
   * isn't unique the train departure will not be added.
   */
  public void userAddDeparture() {
    System.out.println("Enter the train departure´s destination");
    String trainDestination = userInputReader.getUserString();

    System.out.println("Enter the train departure´s departure time on the format hh:mm");
    String departureTime = userInputReader.getUserString();

    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    Pattern pattern = Pattern.compile(patternExample);

    while (!pattern.matcher(departureTime).matches()) {
      System.out.println(TrainDispatchAppUi.RED + "Please make sure the departure "
          + "time is written in correct hh:mm format");
      System.out.println("You entered: " + TrainDispatchAppUi.COLOR_RESET + departureTime);
      System.out.println();
      System.out.println("Please enter a new departure time");
      departureTime = userInputReader.getUserString();
    }

    System.out.println("Enter the train departure´s unique number");
    int trainNumber = userInputReader.getUserInt();

    while (trainNumber < 1 || trainNumber > 99) {
      System.out.println(TrainDispatchAppUi.RED + "Train number must be between 1 and 99"
          + TrainDispatchAppUi.COLOR_RESET);
      System.out.println();
      System.out.println("Please enter a new unique train number");
      trainNumber = userInputReader.getUserInt();
    }

    System.out.println("Enter the departure´s line");
    String trainLine = userInputReader.getUserString();

    System.out.println("Enter the track where the train will depart from");
    int trainTrack = userInputReader.getUserInt();
    while (trainTrack > 100 || trainTrack < 1) {
      System.out.println(TrainDispatchAppUi.RED + "Track number must be between 1 and 99"
          + TrainDispatchAppUi.COLOR_RESET);
      System.out.println();
      System.out.println("Please enter a new track");
      trainTrack = userInputReader.getUserInt();
    }

    String amountDelayed = "";

    boolean departureAdded = this.trainDepartureRegister.addDeparture(departureTime, trainLine,
        trainNumber, trainDestination, trainTrack, amountDelayed);


    if (!departureAdded) {
      System.out.println(TrainDispatchAppUi.RED + "Adding train departure failed");
      System.out.println("Please make sure the train number is unique, not assigned to another "
          + "departure and is between 1 and 99" + TrainDispatchAppUi.COLOR_RESET);
    } else {
      System.out.println(TrainDispatchAppUi.GREEN + "Train departure added successfully"
          + TrainDispatchAppUi.COLOR_RESET);
    }
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
