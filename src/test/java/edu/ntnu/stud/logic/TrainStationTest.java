package edu.ntnu.stud.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainStationTest {

  private TrainDepartureRegister trainStation;


  @BeforeEach
  public void setupTestFixture() {
    this.trainStation = new TrainDepartureRegister();
    this.trainStation.addDeparture("20:30", "L1", 19, "Oslo S", "10", "00:00");
    this.trainStation.addDeparture("01:30", "L2", 19, "Oslo S", "10", "00:00");
    this.trainStation.addDeparture("09:30", "L3", 19, "Oslo S", "10", "00:00");
  }

  @Test
  public void addStudentWithValidParameters() {
    this.trainStation.addDeparture("20:30", "L1", 19, "Oslo S", "10", "00:00");

  }


}