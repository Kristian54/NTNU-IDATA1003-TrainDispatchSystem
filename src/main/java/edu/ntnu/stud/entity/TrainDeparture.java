package edu.ntnu.stud.entity;

import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 * Represents a train departure with:.
 * Departure time
 * Line
 * Train Number
 * Destination
 * Track Number
 */

public class TrainDeparture {
  private LocalTime time; // Initial departure time
  private LocalTime delayTime; // Delay time
  private String line; // Train line
  private int trainNumber; // Train number
  private String destination; // Train destination
  private String trackNumber; // Track number

  /**
   * Creates an instance of TrainDepature.
   *
   * @param departureTime    Time of the departure
   * @param trainLine        Train line
   * @param number           Train number
   * @param trainDestination Train destination
   * @param trackNum         Track number
   */

  public TrainDeparture(String departureTime, String trainLine, int number, String trainDestination,
                        String trackNum, String delay) {
    this.setTime(departureTime);
    this.setDelayTime(delay);
    this.setTrainLine(trainLine);
    this.setTrainNumber(number);
    this.setDestination(trainDestination);
    this.setTrackNumber(trackNum);
  }

  /**
   * Returns the delay time in "hh:mm" format.
   *
   * @return delayTime
   */
  public LocalTime getDelayTime() {
    return delayTime;
  }

  /**
   * Returns the initial departure time in "hh:mm" format.
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Returns the expected time of the departure including the delay. If delay is 00:00, it will
   * return an empty string
   *
   * @return sumTime The expected time of the departure as a string in "hh:mm" format.
   */
  public String getExpectedTime() {
    String empty = "";
    LocalTime sumTime = time.plusHours(delayTime.getHour()).plusMinutes(delayTime.getMinute());
    if (delayTime.equals(LocalTime.parse("00:00"))) { // If delay is 00:00, return empty string

      return empty;
    } else {
      return sumTime.toString();
    }
  }

  /**
   * Returns the train line.
   *
   * @return line
   */
  public String getLine() {
    return line;
  }

  /**
   * Returns the train number.
   *
   * @return trainNumber
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns the train destination.
   *
   * @return destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns the track number.
   *
   * @return trackNumber
   */
  public String getTrackNumber() {
    return trackNumber;
  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the initial time provided is written in valid hh:mm format.
   * If it is not, the time will be set to 00:00.
   *
   * @param initialTime the initial time of the train departure.
   */
  private void setTime(String initialTime) {
    // Example pattern for a string in hh:mm format
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample); // Compiles the pattern

    if (initialTime == null) { // If the initial time is empty, set time to 00:00
      time = LocalTime.parse("00:00");
      // Else if the initial time matches the pattern, set time to the initial time
    } else if (pattern.matcher(initialTime).matches()) {
      time = LocalTime.parse(initialTime);
    } else { // Else set time to 00:00
      time = LocalTime.parse("00:00");
    }
  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the delay time provided is written in
   * valid hh:mm format. If it is not, the time will be set to 00:00.
   * //TODO FINISH JAVA DOC (BOOLEAN)
   *
   * @param delayedTime the time the train is delayed.
   */
  public boolean setDelayTime(String delayedTime) {
    // Example pattern for a string in hh:mm format
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample); // Compiles the pattern
    boolean delayed = false;

    if (delayedTime == null) { // If the delay time is empty, set time to 00:00
      delayTime = LocalTime.parse("00:00");
      // Else if the delay time matches the pattern, set time to the delay time
    } else if (pattern.matcher(delayedTime).matches()) {
      delayTime = LocalTime.parse(delayedTime);
        delayed = true;
    } else { // Else set time to 00:00
      delayTime = LocalTime.parse("00:00");
    }
    return delayed;
  }

  /**
   * checks if the track number given is empty,
   * if it is empty it will be set as an empty string.
   * TODO: Add validation, must be between 0 and 250
   * @param newTrackNumber the track number of the train.
   */

  public void setTrackNumber(String newTrackNumber) {
    if (newTrackNumber == null) { // If the track number is empty, set track number to -1
      trackNumber = "-1";
    } else { // Else set track number to the track number
      trackNumber = newTrackNumber;
    }
  }

  /**
   * Checks if the train number provided is:
   * Positive and either a 1 or 2 digit number.
   * If both conditions aren't met, the train number will be set to 0.
   *
   * @param trainNum the train number of the train.
   */
  private void setTrainNumber(int trainNum) {
    // If the train number is positive and either a 1 or 2 digit number,
    // set train number to the train number
    if (trainNum > 0 && trainNum < 100) {
      trainNumber = trainNum;
    } else { // Else set train number to 0
      trainNumber = 0;
    }
  }

  /**
   * Checks if the train line provided is empty,
   * if it is empty it will be set as an empty string.
   *
   * @param trainLine the train line of the train.
   */
  private void setTrainLine(String trainLine) {
    if (trainLine == null) { // If the train line is empty, set train line to an empty string
      line = "";
    } else { // Else set train line to the train line
      line = trainLine;
    }
  }

  /**
   * Checks if the destination provided is empty.
   * If it is empty it will be set as an empty string.
   *
   * @param trainDestination the destination of the train.
   */
  private void setDestination(String trainDestination) {
    // If the destination is empty, set destination to an empty string
    if (trainDestination == null) {
      destination = "";
    } else { // Else set destination to the destination
      destination = trainDestination;
    }
  }
}