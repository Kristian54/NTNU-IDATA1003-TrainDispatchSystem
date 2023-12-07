package edu.ntnu.stud.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the TrainDeparture class.
 *
 * The following functionality must be tested:
 * <ul>
 *   <li>
 *     Positive tests:
 *     Test creation of TrainDeparture object with valid parameters.
 *     departureTime must be a valid 24h timestamp written on the hh:mm format
 *     trainLine must be a valid String, not null.
 *     number must be an int between 0 and 100.
 *     trainDestination must be a valid String, not null.
 *     trackNum must be an int between 0 and 100.
 *     delay departureTime must be a valid 24h timestamp written on the hh:mm format.
 *   </li>
 *   <li>
 *     Negative tests:
 *     Test that the class is able to deal with all possible invalid parameters when creating an
 *     instance.
 *     If departure time is written any other way than
 *     valid hh:mm format it should be set to "00:00"
 *     If trainLine isn´t a valid String or null, it will be set to an empty String "".
 *     If number isn´t an int between 0 and 100 it will be set to -1.
 *     If trainDestination isn´t a valid string or null it will be set to an empty string "".
 *     If trackNum isn´t an int between 0 and 100 it will be set to -1.
 *     If delay isn´t written on the correct hh:mm format it will be set to "00:00"
 *   </li>
 * </ul>
 */

public class TrainDepartureTest {

private TrainDeparture trainDepartureValid;
private TrainDeparture trainDepartureInvalid;

  /**
   * Test creation of object with valid parameters before each test. This also tests all set
   * methods in the class as they are all used in the constructor to create an instance.
   */
  @BeforeEach
  void creationWithValidParameters() {
    trainDepartureValid = new TrainDeparture("10:30","L21", 10, "Destination", 1, "00:10");
  }

  /**
   * Test creation of object with invalid parameters before each test. This also tests all set
   * methods in the class as they are all used in the constructor to create an instance.
   */
  @BeforeEach
  void creationWithInvalidParameters() {
    trainDepartureInvalid = new TrainDeparture("", null, -40, null, 1000, "26:40");
  }

  /**
   * Tests that all the values was set to the correct values given by the parameters. ALso
   * tests all get/set methods.
   */
  @Test
  void testObjectCreationWithValidParametersAndGetMethods() {
    assertEquals(LocalTime.of(10, 30), trainDepartureValid.getDepartureTime());
    assertEquals("L21", trainDepartureValid.getTrainLine());
    assertEquals(10, trainDepartureValid.getTrainNumber());
    assertEquals("Destination", trainDepartureValid.getDestination());
    assertEquals(1, trainDepartureValid.getTrackNumber());
    assertEquals(LocalTime.of(0, 10), trainDepartureValid.getDelayTime());
    assertEquals("10:40", trainDepartureValid.getExpectedTime());
  }

  /**
   * Tests that the class is able to deal with invalid parameters and return expected values. Also
   * tests all the get/set methods.
   */

  @Test
  void testObjectCreationWithInvalidParametersAndGetMethods() {
    assertEquals(LocalTime.of(0,0), trainDepartureInvalid.getDepartureTime());
    assertEquals("", trainDepartureInvalid.getTrainLine());
    assertEquals(-1, trainDepartureInvalid.getTrainNumber());
    assertEquals("", trainDepartureInvalid.getDestination());
    assertEquals(-1, trainDepartureInvalid.getTrackNumber());
    assertEquals(LocalTime.of(0,0), trainDepartureInvalid.getDelayTime());
    assertEquals("", trainDepartureInvalid.getExpectedTime());
  }
}
