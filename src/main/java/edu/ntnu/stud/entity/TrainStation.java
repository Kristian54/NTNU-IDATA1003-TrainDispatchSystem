package edu.ntnu.stud.entity;

import edu.ntnu.stud.logic.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

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
public class TrainStation {
  private ArrayList<TrainDeparture> trainStation;

  /**
   * Creates an instance of TrainStation.
   */
  public TrainStation() {
    this.trainStation = new ArrayList<>();
  }

  /**
   * Returns the entire register
   *
   * @return trainStation the entire register
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
    boolean trainDepartureAdded = true;
    for (TrainDeparture trainDeparture : trainStation) {
      if (trainDeparture.getTrainNumber() == trainNumber) {
        trainDepartureAdded = false;
      }
    }

    if (trainDepartureAdded == true) {
      TrainDeparture departure =
          new TrainDeparture(departureTime, trainLine, trainNumber, destination, track, delay);
      this.trainStation.add(departure);
    }
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
