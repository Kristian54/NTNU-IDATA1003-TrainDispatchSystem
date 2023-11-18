package edu.ntnu.stud.logic;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

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
public class TrainDepartureRegister {
  private ArrayList<TrainDeparture> departureRegister;

  /**
   * Creates an instance of TrainStation.
   */
  public TrainDepartureRegister() {
    this.departureRegister = new ArrayList<>();
  }

  /**
   * Returns the train departure register.
   *
   * @return trainStation the train departure register
   */

  private ArrayList<TrainDeparture> getDepartureRegister() {
    return departureRegister;
  }

  /**
   * Returns the register sorted by time.
   *
   * @return sortedDepartures the register sorted by time.
   */

  public ArrayList getDepartureRegisterSortedByTime() {
    // Comparator for comparing train departures by time. Suggested by ChatGPT.
    Comparator<TrainDeparture> byLocalTime = Comparator.comparing(TrainDeparture::getTime);

    ArrayList<TrainDeparture> sortedDepartures = new ArrayList<>(this.getDepartureRegister());
    sortedDepartures.sort(byLocalTime);

    return sortedDepartures;
  }

  /**
   * Returns the departureRegister´s iterator.
   *
   * @return the departureRegisters´s iterator
   */
  public Iterator getDepartureRegisterIterator() {
    return departureRegister.iterator();
  }

  /**
   * Returns the train departure with the given train number. If no train departure
   * is found null is returned.
   *
   * @param trainNumber the train number of the train departure to return.
   * @return trainDeparture the train departure with the given train number.
   */
  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    TrainDeparture trainDeparture = null;
    for (TrainDeparture departure : departureRegister) {
      if (departure.getTrainNumber() == trainNumber) {
        trainDeparture = departure;
      }
    }
    return trainDeparture;
  }

  /**
   * Returns a filtered register containing the departures with the given destination.
   *
   * @param destination the given destination for the train departures to return
   * @return train departures with the given destination
   */
  public ArrayList<TrainDeparture> getTrainDepartureByDestination(String destination) {
    String destinationUppercase = destination.toUpperCase();
    ArrayList<TrainDeparture> filteredDepartures = new ArrayList<>();
    Iterator<TrainDeparture> iterator = getDepartureRegisterIterator();

    while (iterator.hasNext()) {
      TrainDeparture departure = iterator.next();
      if (departure.getDestination().toUpperCase().equals(destinationUppercase)) {
        filteredDepartures.add(departure);
      }
    }
    return filteredDepartures;
  }

  /**
   * Removes a given train departure from the trainstation.
   *
   * @param trainDeparture the train departure to remove.
   * @return removed true if removed, false if otherwise.
   */
  public boolean removeDeparture(TrainDeparture trainDeparture) {
    boolean removed = false;
    if (trainDeparture != null) {
      for (TrainDeparture departure : departureRegister) {
        if (departure.getTrainNumber() == trainDeparture.getTrainNumber()) {
          removed = true;
        }
      }
      departureRegister.remove(trainDeparture);
    }
    return removed;
  }

  /**
   * Updates the register and removes any train departure with departure time before current time.
   */
  public void removePassedDepartures() {
    LocalTime currentTime = TrainStationTime.getTrainStationTime();

    Iterator<TrainDeparture> iterator = departureRegister.iterator();
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      LocalTime departureTime = trainDeparture.getTime();
      int delayHours = trainDeparture.getDelayTime().getHour();
      int delayMinutes = trainDeparture.getDelayTime().getMinute();

      LocalTime sumTime = departureTime.plusHours(delayHours).plusMinutes(delayMinutes);

      if (sumTime.isBefore(currentTime)) {
        iterator.remove();
      }
    }
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
   *
   * @return trainNumberAdded True if added, false if train number is not unique
   */


  public boolean addDeparture(String departureTime, String trainLine, int trainNumber,
                              String destination, int track, String delay) {
    // Saves paramteres in a local variable called departure
    TrainDeparture departure =
        new TrainDeparture(departureTime, trainLine, trainNumber, destination, track, delay);

    // Checks if the train number is unique
    boolean trainDepartureAdded = true;
    for (TrainDeparture trainDeparture : departureRegister) {
      // If the train number isn´t unique, the train departure will not be added
      if (trainDeparture.getTrainNumber() == trainNumber) {
        trainDepartureAdded = false;
      }
    }
    // If the train number is unique, the train departure will be added
    if (trainDepartureAdded && departure != null) {
      this.departureRegister.add(departure);
    }
    // True if added, false if not added
    return trainDepartureAdded;
  }

  /**
   * Fills the train station with 10 dummy departures for testing purposes only.
   */
  public void fillTrainStationWithDummyDepartures() {
    addDeparture("10:00", "1", 8, "Stryn", 11, "00:00");
    addDeparture("01:40", "L21", 23, "Ålesund", 2, "00:10");
    addDeparture("17:00", "R", 8, "Moss", 34, "20:20");
    addDeparture("03:00", "403", 43, "Oslo", 4, "00:00");
    addDeparture("14:30", "20", 51, "Gursken", 52, "00:40");
    addDeparture("10:00", "R", 11, "Moss", 0, "20:20");
    addDeparture("23:00", "403", 4, "Oslo", 9, "00:00");
    addDeparture("04:30", "20", 5, "Gursken", 52, "00:40");
    addDeparture("20:00", "R", 9, "Moss", 34, "20:20");
    addDeparture("03:00", "403", 3, "Oslo", 4, "00:00");
  }

}
