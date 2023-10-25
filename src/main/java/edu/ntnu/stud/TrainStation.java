package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
  private HashSet<TrainDeparture> trainStation;
  //private ArrayList<TrainDeparture> trainStation;
  private LocalTime globalTime;

  /**
   * Creates an instance of TrainStation.
   */
  public TrainStation() {
    this.trainStation = new HashSet<>();
    //this.trainStation = new ArrayList<>();
  }

  /**
   * Returns the global time.
   */
  public LocalTime getGlobalTime() {
    return globalTime;
  }

  /**
   * Prints the global time.
   */
  public void printGlobalTime() {
    System.out.println("Global time: " + globalTime);
  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the initial time provided is written in valid hh:mm format.
   * If it is not, the time will be set to 00:00.
   *
   * @param globalTimeInput the initial time of the train departure.
   */
  public void setGlobalTime(String globalTimeInput) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);

    if (globalTimeInput == null) {
      this.globalTime = LocalTime.parse("00:00");
    } else if (pattern.matcher(globalTimeInput).matches()) {
      this.globalTime = LocalTime.parse(globalTimeInput);
    } else {
      this.globalTime = LocalTime.parse("00:00");
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
   */

  public boolean addDeparture(String departureTime, String trainLine, int trainNumber,
                           String destination, String track, String delay) {
    boolean trainNumberAdded = true;
    for (TrainDeparture trainDeparture : trainStation) {
      if (trainDeparture.getTrainNumber() == trainNumber) {
        trainNumberAdded = false;
      }
    }

    if (trainNumberAdded == true) {
      TrainDeparture departure =
          new TrainDeparture(departureTime, trainLine, trainNumber, destination, track, delay);
      this.trainStation.add(departure);
    }
  return trainNumberAdded;
  }

  /**
   * Fills the train station with 5 dummy departures for testing purposes only.
   */
  public void fillTrainStationWithDummyDepartures() {
    addDeparture("10:00","1",8, "Stryn", "11", "00:00");
    addDeparture("01:40", "L21", 23, "Ålesund", "2", "00:10");
    addDeparture("10:00", "R", 8, "Moss", "34", "20:20");
    addDeparture("03:00", "403", 43, "Oslo", "4", "00:00");
    addDeparture("04:30", "20", 51, "Gursken", "52", "00:40");
  }

  /**
   * Prints the details of one train departure.
   *
   * @param trainDeparture the train departure to print.
   */
  public void printDeparture(TrainDeparture trainDeparture) {
    String departureTime = String.format("%-8s", trainDeparture.getTime())
        + String.format("%-5s", trainDeparture.getLine())
        + String.format("%-15s", trainDeparture.getDestination());
    String line = String.format("%-18s", trainDeparture.getExpectedTime());
    String trackNumber = String.format("%-10s", trainDeparture.getTrackNumber());
    String destination = String.format("%-13s", trainDeparture.getTrainNumber());
    System.out.println(departureTime + "  "
        + line + " | " + trackNumber + " | " + destination);
  }
  /**
   * Prints the details of all departures in the train station to the console.
   */

  public void printDepartures() {
    for (TrainDeparture trainDeparture : this.trainStation) {
      printDeparture(trainDeparture);
    }
  }

  /**
   * Comparator for comparing train departures by time. Suggested by ChatGPT.
   */
  Comparator<TrainDeparture> byLocalTime = Comparator.comparing(TrainDeparture::getTime);

  /**
   * Prints the details of all departures in the train station sorted by time to the console.
   */
  public void printDeparturesSortedByTime() {
    ArrayList<TrainDeparture> sortedDepartures = new ArrayList<>(this.trainStation);
    sortedDepartures.sort(byLocalTime);

    for (TrainDeparture trainDeparture : sortedDepartures) {
      printDeparture(trainDeparture);
    }
  }
}
