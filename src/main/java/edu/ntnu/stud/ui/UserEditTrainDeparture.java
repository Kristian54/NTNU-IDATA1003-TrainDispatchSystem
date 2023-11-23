package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.Scanner;

public class UserEditTrainDeparture {

  private TrainDepartureRegister trainDepartureRegister;
  private UserInputReader userInputReader;

  /**
   * Creates an instance of UserEditTrainDeparture
   *
   * @param trainDepartureRegister trainDepartureRegister
   */
  public UserEditTrainDeparture(TrainDepartureRegister trainDepartureRegister) {
    this.trainDepartureRegister = trainDepartureRegister;
    this.userInputReader = new UserInputReader();
  }

  /**
   * Lets the user add a delay to an existing departure.
   * If it is not written on the format "hh:mm" or no train departure is found with the given train
   * number the user will be notified accordingly.
   */
  public void userAddDelay() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to add a delay to:");
    int trainNumber = userInputReader.getUserInt();

    TrainDeparture departureToDelay =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);
    if (departureToDelay == null) {
      System.out.println(TrainDispatchAppUi.RED + "Train departure not found" + TrainDispatchAppUi.COLOR_RESET);
    } else {
      System.out.println("Enter the delay time on the format hh:mm:");
      String delay = scanner.next();
      boolean delayAdded = departureToDelay.setDelayTime(delay);
      if (delayAdded) {
        System.out.println(TrainDispatchAppUi.GREEN + "Delay added successfully" + TrainDispatchAppUi.COLOR_RESET);
        scanner.nextLine();
      } else {
        System.out.println(TrainDispatchAppUi.RED + "Delay not added");
        System.out.println("Please make sure the time is written in the format hh:mm");
        System.out.println("You entered: " + TrainDispatchAppUi.COLOR_RESET + delay);
        scanner.nextLine();
      }
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user add a track number to an existing departure.
   * Track number must be between 1 and 99, if it isn't the user will be notified accordingly.
   */
  public void userAddTrack() {
    System.out.println("Enter the train number of the departure you would like to add a track to:");
    int trainNumber = userInputReader.getUserInt();

    TrainDeparture departureToAddTrack =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToAddTrack != null) {
      System.out.println("Please enter a track number between 1 and 99");
      int track = userInputReader.getUserInt();
      if (track < 100 && track > 0) {
        departureToAddTrack.setTrackNumber(track);
        System.out.println(TrainDispatchAppUi.GREEN + "Track added" + TrainDispatchAppUi.COLOR_RESET);
      } else {
        System.out.println(TrainDispatchAppUi.RED + "Track must be between 1 and 99" + TrainDispatchAppUi.COLOR_RESET);
      }
    } else {
      System.out.println(TrainDispatchAppUi.RED + "Train departure not found" + TrainDispatchAppUi.COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }
}
