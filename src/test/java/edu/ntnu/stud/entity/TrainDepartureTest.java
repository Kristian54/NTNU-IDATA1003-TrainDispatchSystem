package edu.ntnu.stud.entity;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class TrainDepartureTest {

  @Test
  public void testCreationOfTrainDepartureWithValidInputWithoutDelay() {
    TrainDeparture trainDeparture = new TrainDeparture("20:30", "L21", 19, "Oslo S", 10, "00:00");
    assertEquals(LocalTime.parse("20:30"), trainDeparture.getTime());
    assertEquals("L21", trainDeparture.getLine());
    assertEquals(19, trainDeparture.getTrainNumber());
    assertEquals("Oslo S", trainDeparture.getDestination());
    assertEquals("10", trainDeparture.getTrackNumber());
    assertEquals(LocalTime.parse("00:00"), trainDeparture.getDelayTime());
  }

  @Test
  public void testCreationOfTrainDepartureWithValidInputWithDelay() {
    TrainDeparture trainDeparture = new TrainDeparture("20:30", "L21", 19, "Oslo S", 10, "02:30");
    assertEquals("23:00", trainDeparture.getExpectedTime());
    assertEquals("L21", trainDeparture.getLine());
    assertEquals(19, trainDeparture.getTrainNumber());
    assertEquals("Oslo S", trainDeparture.getDestination());
    assertEquals("10", trainDeparture.getTrackNumber());
    assertEquals(LocalTime.parse("02:30"), trainDeparture.getDelayTime());
  }

  @Test
  public void testCreationOfTrainDepartureWithInvalidParameters() {
    TrainDeparture trainDeparture = new TrainDeparture("test", "", -20, "", 999, "100");
    assertEquals(LocalTime.parse("00:00"), trainDeparture.getTime());
    assertEquals("", trainDeparture.getLine());
    assertEquals(0, trainDeparture.getTrainNumber());
    assertEquals("", trainDeparture.getDestination());
    assertEquals("", trainDeparture.getTrackNumber());
    assertEquals(LocalTime.parse("00:00"), trainDeparture.getDelayTime());
  }



}
