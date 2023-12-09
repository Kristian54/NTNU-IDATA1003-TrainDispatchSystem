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
 *   <li>Add a departure to the register with a unique train number</li>
 *   <li>Remove a departure based on train number</li>
 *   <li>Return departure(s) based on destination</li>
 *   <li>Return all departures sorted by departure time</li>
 *   <li>Remove passed departures</li>
 *   <li>Return the register's iterator</li>
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
   * Returns the register sorted by time.
   * This method does not account for expected time, and will sort by initial departure time. If the
   * register is empty null is returned.
   *
   * @return sortedDepartures the register sorted by time. Null if empty.
   */

  public ArrayList<TrainDeparture> getDepartureRegisterSortedByTime() {
    // Comparator for comparing train departures by time. Suggested by ChatGPT.
    Comparator<TrainDeparture> byLocalTime = Comparator.comparing(TrainDeparture::getDepartureTime);

    ArrayList<TrainDeparture> sortedDepartures = new ArrayList<>(departureRegister);
    sortedDepartures.sort(byLocalTime);

    return sortedDepartures;
  }

  /**
   * Returns the departureRegister´s iterator.
   *
   * @return the departureRegisters´s iterator
   */
  public Iterator<TrainDeparture> getDepartureRegisterIterator() {
    return departureRegister.iterator();
  }

  /**
   * Returns the train departure with the given train number.
   * If no train departure is found with the given train number null is returned.
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
   * @return train departures with the given destination. Null if empty.
   */
  public ArrayList<TrainDeparture> getTrainDepartureByDestination(String destination) {
    String destinationUppercase = destination;
    ArrayList<TrainDeparture> filteredDepartures = new ArrayList<>();
    Iterator<TrainDeparture> iterator = getDepartureRegisterIterator();

    while (iterator.hasNext()) {
      TrainDeparture departure = iterator.next();
      if (departure.getDestination().equalsIgnoreCase(destinationUppercase)) {
        filteredDepartures.add(departure);
      }
    }
    return filteredDepartures;
  }

  /**
   * Removes a given train departure from the register.
   * If no departure with the given train number is found the register will remain unchanged and
   * false is returned.
   *
   * @param trainDeparture the train departure to remove.
   * @return removed true if removed, false if no departure is found.
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
   * Removes passed departures.
   * Updates the register and removes any train departure with departure time before current time.
   */
  public void removePassedDepartures() {
    LocalTime currentTime = TrainStationTime.getTrainStationTime();

    Iterator<TrainDeparture> iterator = getDepartureRegisterIterator();
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      LocalTime departureTime = trainDeparture.getDepartureTime();
      int delayHours = trainDeparture.getAmountDelayed().getHour();
      int delayMinutes = trainDeparture.getAmountDelayed().getMinute();

      LocalTime sumTime = departureTime.plusHours(delayHours).plusMinutes(delayMinutes);

      if (sumTime.isBefore(currentTime)) {
        iterator.remove();
      }
    }
  }

  /**
   * Adds a train departure to the trainstation.
   *
   * @param departureTime the initial time of the departure written in "hh:mm" format.
   * @param trainLine     the train line of the train departure
   * @param trainNumber   train number. Must be unique
   * @param destination   the destination of the train departure
   * @param trainTrack    the track number of the train departure
   * @param delay         the amount a departure is delayed by written in "hh:mm" format. If there
   *                      is no current delay this should be set to an empty string or 00:00.
   *
   * @return trainDepartureAdded True if added, false if not added
   */

  public boolean addDeparture(String departureTime, String trainLine, int trainNumber,
                              String destination, int trainTrack, String delay) {
    // Saves paramteres in a local variable called departure
    TrainDeparture departure =
        new TrainDeparture(departureTime, trainLine, trainNumber, destination, trainTrack, delay);

    // Checks if the train number is unique
    boolean trainDepartureAdded = true;
    for (TrainDeparture trainDeparture : departureRegister) {
      // If the train number isn´t unique, the train departure will not be added
      if (trainDeparture.getTrainNumber() == trainNumber) {
        trainDepartureAdded = false;
      }
    }
    // If the train number is unique, the train departure will be added
    if (trainDepartureAdded) {
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