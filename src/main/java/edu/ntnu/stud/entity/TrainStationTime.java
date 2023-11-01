package edu.ntnu.stud.entity;

import java.time.LocalTime;
import java.util.regex.Pattern;

public class TrainStationTime {

  private LocalTime trainStationTime;

  public TrainStationTime() {
    this.trainStationTime = LocalTime.parse("00:00");
  }

  public LocalTime getTrainStationTime() {
    return trainStationTime;
  }

  /**
   * Parts of the code used under was suggested by GitHub Copilot.
   * Check´s if the initial time provided is written in valid hh:mm format.
   * If it is not, the time will be set to 00:00.
   *
   * @param globalTimeInput the initial time of the train departure.
   */
  public void setTrainStationTime(String globalTimeInput) {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    Pattern pattern = Pattern.compile(patternExample);

    if (globalTimeInput == null) {
      this.trainStationTime = LocalTime.parse("00:00");
    } else if (pattern.matcher(globalTimeInput).matches()) {
      this.trainStationTime = LocalTime.parse(globalTimeInput);
    } else {
      this.trainStationTime = LocalTime.parse("00:00");
    }

  }
}
