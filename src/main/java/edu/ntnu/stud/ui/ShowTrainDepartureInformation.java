package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Prints the details of a train departure to the console.
 *
 * <p>The following functionality is implemented:</p>
 * <ul>
 *   <li>Print a single train departure</li>
 *   <li>Print all departures sorted by departure time</li>
 *   <li>Print the information table</li>
 * </ul>
 */
public class ShowTrainDepartureInformation {

  private TrainDepartureRegister trainDepartureRegister;

  /**
   * Creates an instance of ShowTrainDepartureInformation.
   *
   * @param trainDepartureRegister trainDepartureRegister to print information from.
   */
  public ShowTrainDepartureInformation(TrainDepartureRegister trainDepartureRegister) {
    this.trainDepartureRegister = trainDepartureRegister;
  }

  /**
   * Prints the details of all departures in the departure register sorted by time to the console.
   */
  public void printDeparturesSortedByTime() {
    ArrayList<TrainDeparture> sortedDepartures =
        new ArrayList<>(trainDepartureRegister.getDepartureRegisterSortedByTime());
    for (TrainDeparture trainDeparture : sortedDepartures) {
      printDeparture(trainDeparture);
    }
  }

  /**
   * Prints the details of one train departure to the console.
   * If destination is longer than 17 characters or train line is longer than 4 characters they will
   * be cut down to prevent stretching.
   * Minimum string length is implemented for all variables for the departure to fit in the
   * info table.
   *
   * @param trainDeparture the train departure to print.
   */
  public void printDeparture(TrainDeparture trainDeparture) {
    if (trainDeparture != null) {
      String departureTime = String.format("%-6s", trainDeparture.getDepartureTime());
      String trainLine = String.format("%-5s", trainDeparture.getTrainLine());
      String destination = String.format("%-16s", trainDeparture.getDestination());
      String expectedTime = String.format(TrainDispatchAppUi.YELLOW + "%-18s",
          trainDeparture.getExpectedTime() + TrainDispatchAppUi.COLOR_RESET);
      String trackNumber = String.format("%-10s", trainDeparture.getTrackNumber());
      String trainNumber = String.format("%-13s", trainDeparture.getTrainNumber());

      int destinationMaxLength = 16;
      if (destination.length() > destinationMaxLength) {
        destination = destination.substring(0, 14) + "..";
      }
      int trainLineMaxLength = 5;
      if (trainLine.length() > trainLineMaxLength) {
        trainLine = trainLine.substring(0, trainLineMaxLength);
      }
      if (trainDeparture.getTrackNumber() == -1) {
        trackNumber = TrainDispatchAppUi.RED + "Invalid   " + TrainDispatchAppUi.COLOR_RESET;
      }
      System.out.println(departureTime + "  " + trainLine + destination
          + expectedTime + "     | " + trackNumber + " | " + trainNumber);
    }
  }

  /**
   * Prints the information table containing all departures to the console.
   */
  public void printInfoTable() {
    System.out.println(TrainStationTime.getTrainStationTime()
        + "   Avganger/" + TrainDispatchAppUi.YELLOW + "Departures  "
        + TrainDispatchAppUi.COLOR_RESET
        + "Forventet/" + TrainDispatchAppUi.YELLOW + "expected "
        + TrainDispatchAppUi.COLOR_RESET + "| "
        + "Track/" + TrainDispatchAppUi.YELLOW + "Spor "
        + TrainDispatchAppUi.COLOR_RESET + "|" + " Nummer/"
        + TrainDispatchAppUi.YELLOW + "Number:" + TrainDispatchAppUi.COLOR_RESET);
    this.printDeparturesSortedByTime();
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
