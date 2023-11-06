package edu.ntnu.stud.logic;

import edu.ntnu.stud.entity.TrainDeparture;
import java.util.ArrayList;

/**
 * Represents a trainstation with registered train departures.
 *
 * <p>The following functionality is implemented:
 * <ul>
 *   <li>Add a departure with a unique train number</li>
 *   <li>Remove a departure</li>
 *   <li>Change departure time of an existing departure</li>
 * </ul>
 */
public class TrainStation {
  private ArrayList<TrainDeparture> trainStation;

  /**
   * Creates an instance of TrainStation.
   */
  public TrainStation() {
    this.trainStation = new ArrayList<>();
  }

  /**
   * Returns the train departure register
   *
   * @return trainStation the train departure register
   */
  public ArrayList<TrainDeparture> getTrainStation() {
    return trainStation;
  }

  /**
   * Returns the train departure with the given train number.
   *
   * @param trainNumber
   * @return trainDeparture the train departure with the given train number.
   */
  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    TrainDeparture trainDeparture = null;
    for (TrainDeparture departure : trainStation) {
      if (departure.getTrainNumber() == trainNumber) {
        trainDeparture = departure;
      }
    }
    return trainDeparture;
  }

  /**
   * Adds a train departure to the trainstation.
   *
   * @param departureTime the train departure to add.
   * @param trainLine     the train line of the train departure.
   * @param trainNumber   train number (unique)
   * @param destination   the destination of the train departure.
   * @param track         the track number of the train departure.
   * @param delay         the delay of the train departure.
   * @return trainNumberAdded True if added, false if train number is not unique
   */


  public boolean addDeparture(String departureTime, String trainLine, int trainNumber,
                              String destination, String track, String delay) {
    // Saves paramteres in a local variable called departure
    TrainDeparture departure =
        new TrainDeparture(departureTime, trainLine, trainNumber, destination, track, delay);

    // Checks if the train number is unique
    boolean trainDepartureAdded = true;
    for (TrainDeparture trainDeparture : trainStation) {
      // If the train number isn´t unique, the train departure will not be added
      if (trainDeparture.getTrainNumber() == trainNumber) {
        trainDepartureAdded = false;
      }
    }
      // If the train number is unique, the train departure will be added
    if (trainDepartureAdded && departure != null) {
      this.trainStation.add(departure);
    }
    // True if added, false if not added
    return trainDepartureAdded;
  }

  /**
   * Fills the train station with 5 dummy departures for testing purposes only.
   */
  public void fillTrainStationWithDummyDepartures() {
    addDeparture("10:00", "1", 8, "Stryn", "11", "00:00");
    addDeparture("01:40", "L21", 23, "Ålesund", "2", "00:10");
    addDeparture("10:00", "R", 8, "Moss", "34", "20:20");
    addDeparture("03:00", "403", 43, "Oslo", "4", "00:00");
    addDeparture("04:30", "20", 51, "Gursken", "52", "00:40");
  }

}
