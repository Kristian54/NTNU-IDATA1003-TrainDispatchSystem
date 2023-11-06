package edu.ntnu.stud.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class TrainStationTest {

  private TrainStation trainStation;


  @BeforeEach
  public void setupTestFixture() {
    this.trainStation = new TrainStation();
    this.trainStation.addDeparture("20:30", "L1", 19, "Oslo S", "10", "00:00");
    this.trainStation.addDeparture("01:30", "L2", 19, "Oslo S", "10", "00:00");
    this.trainStation.addDeparture("09:30", "L3", 19, "Oslo S", "10", "00:00");

  }


}