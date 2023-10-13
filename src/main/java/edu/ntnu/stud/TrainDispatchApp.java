package edu.ntnu.stud;

import java.sql.SQLOutput;
import java.util.Iterator;

/**
 * This is the main class for the train dispatch application.
 */
  // TODO: Fill in the main method and any other methods you need.

public class TrainDispatchApp {
  private TrainStation trainStation;

  /**
   * Creates an instance of TrainDispatchApp
   */
  public TrainDispatchApp() {
    this.trainStation = new TrainStation();
  }

  /**
   * Start method where the application will be run
   */
  public void start() {
trainStation.fillTrainStationWithDummyDepartures();
trainStation.printDepartures();
  }


  public static void main(String[] args) {
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();
  }
}
