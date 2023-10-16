package edu.ntnu.stud;

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
  private LocalTime time;
  private LocalTime delayTime;
  private String line;
  private int trainNumber;
  private String destination;
  private String trackNumber;

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
    setTime(departureTime);
    setDelayTime(delay);
    setTrainLine(trainLine);
    setTrainNumber(number);
    setDestination(trainDestination);
    setTrackNumber(trackNum);
  }

  /**
   * Returns the departure time.
   *
   * @return time
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Returns the delay time.
   *
   * @return delayTime
   */
  public LocalTime getDelayTime() {
    return delayTime;
  }

  /**
   * Returns the delaytime in a string format for printing to console.
   * If the delaytime is 00:00, it will return an empty string.
   *
   * @return delayTime.toString()
   */
  public String getDelayTimePrint() {
    String empty = "";
    if (delayTime.equals(LocalTime.parse("00:00"))) {
      return empty;
    } else {
      return delayTime.toString();
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
  public void setTime(String initialTime) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);

    if (initialTime == null) {
      time = LocalTime.parse("00:00");
    } else if (pattern.matcher(initialTime).matches()) {
      time = LocalTime.parse(initialTime);
    } else {
      time = LocalTime.parse("00:00");
    }

  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the delay time provided is written in
   * valid hh:mm format. If it is not, the time will be set to 00:00.
   *
   * @param delayedTime the time the train is delayed.
   */
  public void setDelayTime(String delayedTime) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);

    if (delayedTime == null) {
      delayTime = LocalTime.parse("00:00");
    } else if (pattern.matcher(delayedTime).matches()) {
      delayTime = LocalTime.parse(delayedTime);
    } else {
      delayTime = LocalTime.parse("00:00");
    }
  }
  /**
   * checks if the track number given is empty,
   * if it is empty it will be set as an empty string.
   *
   * @param newTrackNumber the track number of the train.
   */

  public void setTrackNumber(String newTrackNumber) {
    if (newTrackNumber == null) {
      trackNumber = "";
    } else {
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
  public void setTrainNumber(int trainNum) {
    if (trainNum > 0 && trainNum < 100) {
      trainNumber = trainNum;
    } else {
      trainNumber = 0;
    }
  }

  /**
   * Checks if the train line provided is empty,
   * if it is empty it will be set as an empty string.
   *
   * @param trainLine the train line of the train.
   */
  public void setTrainLine(String trainLine) {
    if (trainLine == null) {
      line = "";
    } else {
      line = trainLine;
    }
  }

  /**
   * Checks if the destination provided is empty.
   * If it is empty it will be set as an empty string.
   *
   * @param trainDestination the destination of the train.
   */
  public void setDestination(String trainDestination) {
    if (trainDestination == null) {
      destination = "";
    } else {
      destination = trainDestination;
    }
  }
}