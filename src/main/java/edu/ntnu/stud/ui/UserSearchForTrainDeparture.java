package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.ArrayList;
import java.util.Scanner;

public class UserSearchForTrainDeparture {
  private TrainDepartureRegister trainDepartureRegister;
  private UserInputReader userInputReader;
  private ShowTrainDepartureInformation showTrainDepartureInformation;

  /**
   * Creates an instance of UserSearchForTrainDeparture
   *
   * @param trainDepartureRegister trainDepartureRegister
   */
  public UserSearchForTrainDeparture(TrainDepartureRegister trainDepartureRegister) {
    this.trainDepartureRegister = trainDepartureRegister;
    this.userInputReader = new UserInputReader();
    this.showTrainDepartureInformation = new ShowTrainDepartureInformation(trainDepartureRegister);
  }

  /**
   * Lets the user search for a train departure with the given train number.
   * If no train departure is found with the given train number the user will be notified.
   */
  public void userSearchByTrainNumber() {
    System.out.println("Enter the train number of the departure you would like to view:");
    int trainNumber = userInputReader.getUserInt();

    TrainDeparture departureToPrint =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToPrint != null) {
      showTrainDepartureInformation.printDeparture(departureToPrint);
    } else {
      System.out.println(TrainDispatchAppUi.RED + "Train departure not found" + TrainDispatchAppUi.COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  /**
   * Lets the user search for all train departures with the given location.
   * If no departures with the given destination is found, the user will be notified.
   */
  public void userSearchByDestination() {
    System.out.println("Enter the destination of the departures you would like to view:");
    ArrayList<TrainDeparture> filteredDepartures =
        this.trainDepartureRegister.getTrainDepartureByDestination(userInputReader.getUserString());
    if (filteredDepartures.isEmpty()) {
      System.out.println(TrainDispatchAppUi.RED + "No departures found" + TrainDispatchAppUi.COLOR_RESET);
    } else {
      System.out.println(TrainStationTime.getTrainStationTime()
      + "   Avganger/" + TrainDispatchAppUi.YELLOW + "Departures  " + TrainDispatchAppUi.COLOR_RESET
          + "Forventet/" + TrainDispatchAppUi.YELLOW + "expected " + TrainDispatchAppUi.COLOR_RESET + "| "
          + "Track/" + TrainDispatchAppUi.YELLOW + "Spor "
          + TrainDispatchAppUi.COLOR_RESET + "|" + " Nummer/" + TrainDispatchAppUi.YELLOW + "Number:" + TrainDispatchAppUi.COLOR_RESET);
      for (TrainDeparture trainDeparture : filteredDepartures) {
        showTrainDepartureInformation.printDeparture(trainDeparture);
      }
    }
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

}
