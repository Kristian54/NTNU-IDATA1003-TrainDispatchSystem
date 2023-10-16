package edu.ntnu.stud;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This is the main class for the train dispatch application.
 */
// TODO: Fill in the main method and any other methods you need.

public class TrainDispatchAppUi {
  private TrainStation trainStation;

  /**
   * Creates an instance of TrainDispatchApp.
   */
  public TrainDispatchAppUi() {
    this.trainStation = new TrainStation();
  }

  /**
   * Prints the information table.
   */
  public void printInfoTable() {
    System.out.println("Destination:    | Departure Time: | Delay: | "
        + "Line: | Train number: | Track Number: | Current Time: "
        + trainStation.getGlobalTime());
    trainStation.printDeparturesSortedByTime();
  }

  /**
   * Start method where the application will be run.
   */
  public void start() {
    trainStation.setGlobalTime("23:00");
    trainStation.fillTrainStationWithDummyDepartures();
    printInfoTable();
  }
}
