package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.Iterator;

public class TrainStation {
  /**
   * Represents a trainstation with registered train departures.
   *
   * <p>The following functionality is implemented:
   * <ul>
   *   <li>Add a departure with a unique train number</li>
   *   <li>Remove a departure</li>
   *   <li>Change departure time of an existing departure</li>
   *   <li>Print all departures sorted by time</li>
   * </ul>
   */

  public ArrayList<TrainDeparture> trainStation;

  /**
   * Creates an instance of TrainStation
   */
  public TrainStation() {
    this.trainStation = new ArrayList<>();
  }

  /**
   * Adds a train departure to the trainstation
   *
   * @param trainDeparture
   */
  public void addDeparture(TrainDeparture trainDeparture) {
    this.trainStation.add(trainDeparture);
  }

  /**
   * Fills the train station with 5 dummy departures for testing purposes only.
   */
  public void fillTrainStationWithDummyDepartures(){
    this.trainStation.add(new TrainDeparture("00:00", "L1", 1, "Trondheim", "1", "00:00"));
    this.trainStation.add(new TrainDeparture("01:00", "L2", 2, "Ålesund", "2", "00:10"));
    this.trainStation.add(new TrainDeparture("02:00", "L3", 3, "Moss", "3", "00:20"));
    this.trainStation.add(new TrainDeparture("03:00", "L4", 4, "Oslo", "4", "00:30"));
    this.trainStation.add(new TrainDeparture("04:00", "L5", 5, "Vestby", "5", "00:40"));
  }

  /**
   * Prints the details of one train departure
   * @param trainDeparture
   */
  public void printDeparture(TrainDeparture trainDeparture) {
    System.out.println(
        "Departure time: " + trainDeparture.getTime() +
            ", Delay: " + trainDeparture.getDelayTime() +
            ", Line: " + trainDeparture.getLine() +
            ", Train number: " + trainDeparture.getTrainNumber() +
            ", Destination: " + trainDeparture.getDestination() +
            ", Track number: " + trainDeparture.getTrackNumber());

  }
  /**
   * Prints the details of all departures in the train station to the console.
   */
  public void printDepartures() {
    for (TrainDeparture trainDeparture : this.trainStation) {
      printDeparture(trainDeparture);
    }
  }

}
