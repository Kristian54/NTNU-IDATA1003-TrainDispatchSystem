package edu.ntnu.stud.entity;

import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 * Represents the current time in the system.
 */
public class TrainStationTime {

  /**
   * The time in "hh:mm" format.
   */
  private static LocalTime trainStationTime;

  /**
   * Creates an instance of TrainStationTime.
   */
  public TrainStationTime() {
    trainStationTime = LocalTime.parse("00:00");
  }

  /**
   * Returns the time in "hh:mm" format.
   *
   * @return trainStationTime the time in "hh:mm" format.
   */
  public static LocalTime getTrainStationTime() {
    return trainStationTime;
  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the initial time provided is written in valid hh:mm format and if the given time is
   * after the current time.
   * If it is not, the time will be set to 00:00.
   *
   * @param globalTimeInput the updated time.
   */
  public boolean setTrainStationTime(String globalTimeInput) {
    boolean clockUpdated = false;
    // Example pattern for a string in hh:mm format.
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);
    // If pattern matches example pattern and is after trainStationTime, set trainStationTime to
    // parameter globalTimeInput.
    if (pattern.matcher(globalTimeInput).matches() &&
        LocalTime.parse(globalTimeInput).isAfter(trainStationTime)) {
      trainStationTime = LocalTime.parse(globalTimeInput);
      clockUpdated = true;
    }
  return clockUpdated;
  }
}
