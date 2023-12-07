package edu.ntnu.stud.entity;

import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 * Represents the current time in the application in "hh:mm" format.
 *
 * @author kristiangarder
 */
public class TrainStationTime {

  // The current time in the application.
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
   * Sets the time in "hh:mm" format.
   * The given time must be written in hh:mm format and after the current time. If it isn't it will
   * not be updated.
   * Parts of the code used under was suggested by GitHub Copilot.
   *
   * @param globalTimeInput the time to be set in "hh:mm" format.
   *
   * @return clockUpdated true if the clock was updated, false if not.
   */
  public boolean setTrainStationTime(String globalTimeInput) {
    boolean clockUpdated = false;
    // Example pattern for a string in hh:mm format.
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);
    // If pattern matches example pattern and is after trainStationTime, set trainStationTime to
    // parameter globalTimeInput.
    if (pattern.matcher(globalTimeInput).matches()
        && LocalTime.parse(globalTimeInput).isAfter(trainStationTime)) {
      trainStationTime = LocalTime.parse(globalTimeInput);
      clockUpdated = true;
    }
    return clockUpdated;
  }
}
