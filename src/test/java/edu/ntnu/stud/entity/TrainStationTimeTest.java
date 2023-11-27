package edu.ntnu.stud.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the TrainStationTime class.
 *
 * The following functionality must be tested:
 * <ul>
 *   <li>
 *     Positive tests:
 *     Test creation of TrainStationTime object.
 *     After creating an instance, time should be set to "00:00"
 *     Update time to a valid timestamp after the current time.
 *     Test that setTrainStationTime returns true if time is updated.
 *
 *   </li>
 *   <li>
 *     Negative tests:
 *     Test that setTrainStationTime method can deal with invalid parameters and
 *     that the time cannot be set back in time and the method returns false if it isn´t updated.
 *   </li>
 * </ul>
 */

public class TrainStationTimeTest {
  private TrainStationTime trainStationTime;

  /**
   * Tests creation of object.
   */
  @BeforeEach
  void setUp() {
    trainStationTime = new TrainStationTime();
  }

  /**
   * Tests creation of object and get method.
   */
  @Test
  void testCreationOfObjectAndGetMethod() {
    assertEquals(LocalTime.of(0,0), TrainStationTime.getTrainStationTime());
  }

  /**
   * Tests that the set method returns true and updates time if a valid parameter is given.
   * Tests that the set method returns false if an invalid parameter is given and that the time
   * then is not updated.
   * Tests that the set method returns false if the time given by the parameter is before the
   * previous set time and that the time then is not updated.
   */
  @Test
  void testSetTrainStationTime() {
    assertTrue(trainStationTime.setTrainStationTime("01:00"));
    assertEquals(LocalTime.of(1,0), TrainStationTime.getTrainStationTime());

    assertFalse(trainStationTime.setTrainStationTime("invalid"));
    assertEquals(LocalTime.of(1,0), TrainStationTime.getTrainStationTime());

    assertFalse(trainStationTime.setTrainStationTime("00:30"));
    assertEquals(LocalTime.of(1,0), TrainStationTime.getTrainStationTime());
  }
}
