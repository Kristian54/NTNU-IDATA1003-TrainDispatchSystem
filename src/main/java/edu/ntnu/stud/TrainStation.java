package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

  private ArrayList<TrainDeparture> trainStation;
  private LocalTime globalTime;

  /**
   * Creates an instance of TrainStation.
   */
  public TrainStation() {
    this.trainStation = new ArrayList<>();
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
   * @param trainDeparture the train departure to add.
   */

  public void addDeparture(TrainDeparture trainDeparture) {
    this.trainStation.add(trainDeparture);
  }

  /**
   * Fills the train station with 5 dummy departures for testing purposes only.
   */
  public void fillTrainStationWithDummyDepartures() {
    this.trainStation.add(new TrainDeparture("12:00", "L1", 1, "Trondheim", "11", "00:00"));
    this.trainStation.add(new TrainDeparture("01:40", "L21", 23, "Ålesund", "2", "00:10"));
    this.trainStation.add(new TrainDeparture("10:00", "R", 3, "Moss", "34", "00:20"));
    this.trainStation.add(new TrainDeparture("03:00", "40330", 43, "Oslo", "4", "00:00"));
    this.trainStation.add(new TrainDeparture("04:30", "20", 51, "Vestby", "52", "00:40"));
  }

  /**
   * Prints the details of one train departure.
   *
   * @param trainDeparture the train departure to print.
   */
  public void printDeparture(TrainDeparture trainDeparture) {
    String departureTime = String.format("%-15s", trainDeparture.getDestination());
    String delayTime = String.format("%-15s", trainDeparture.getTime());
    String line = String.format("%-6s", trainDeparture.getDelayTimePrint());
    String trainNumber = String.format("%-5s", trainDeparture.getLine());
    String trackNumber = String.format("%-13s", trainDeparture.getTrainNumber());
    String destination = String.format("%-13s", trainDeparture.getTrackNumber());
    System.out.println(departureTime + " | " + delayTime + " | "
        + line + " | " + trainNumber + " | " + trackNumber + " | " + destination);
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
