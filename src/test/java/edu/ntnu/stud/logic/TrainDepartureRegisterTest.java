package edu.ntnu.stud.logic;

import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the TrainDepartureRegister class.
 * The following functionality must be tested:
 * <ul>
 *   <li>
 *     Positive tests:
 *     <li>Adding a departure with valid parameters should return true.</li>
 *     <li>Register should be returned sorted by initial departure time.</li>
 *     <li>Register should return all departures with given destination.</li>
 *     <li>Register should return departure with given train number.</li>
 *     <li>Register should be able to remove a departure and return true if removed.</li>
 *   </li>
 *   <li>
 *     Negative tests:
 *     <li>Adding a departure with non-unique train number shouldn't be added and return false.</li>
 *     <li>Register should return false if no departure with given train number is found when
 *     removing a departure.</li>
 *   </li>
 * </ul>
 */

class TrainDepartureRegisterTest {

  private TrainDepartureRegister trainDepartureRegister;

  @BeforeEach
  public void setupTestFixture() {
    this.trainDepartureRegister = new TrainDepartureRegister();
    this.trainDepartureRegister.addDeparture("20:30", "L1", 1, "test", 1, "00:00");
    this.trainDepartureRegister.addDeparture("01:30", "L2", 2, "tEst", 2, "01:00");
    this.trainDepartureRegister.addDeparture("09:30", "L3", 3, "1", 3, "02:00");
  }

  /**
   * Tests that a departure can be successfully removed from the register and return true.
   */
  @Test
  public void removeExistingDeparture() {
    assertTrue(trainDepartureRegister.removeDeparture
        (trainDepartureRegister.getTrainDepartureByTrainNumber(1)));
    assertNull(trainDepartureRegister.getTrainDepartureByTrainNumber(1));
  }

  /**
   * Tests that false is returned if no departure is found with the given train number to remove.
   */
  @Test
  public void removeNonExistingDeparture() {
    assertFalse(trainDepartureRegister.removeDeparture
        (trainDepartureRegister.getTrainDepartureByTrainNumber(10)));
  }

  /**
   * Tests that a departure can successfully be added to the register and returned by
   * train number.
   */
  @Test
  void addTrainDepartureWithValidParameters() {
    TrainDeparture trainDeparture = trainDepartureRegister.getTrainDepartureByTrainNumber(2);
    assertEquals(trainDeparture.getDepartureTime().toString(), "01:30");
  }

  /**
   * Tests that a departure can not be added if the train number isn't unique and returns false.
   */
  @Test
  void addTrainDepartureWithExistingTrainNumber() {
    assertFalse(this.trainDepartureRegister.addDeparture("20:30", "Test", 1, "1", 1, "00:00"));
    TrainDeparture departure = trainDepartureRegister.getTrainDepartureByTrainNumber(1);
    assertEquals(departure.getTrainLine(), "L1");
  }

  /**
   * Validates that the register can be returned sorted by time.
   */
  @Test
  void sortRegisterByTime() {
    ArrayList<TrainDeparture> sortedDepartures =
        trainDepartureRegister.getDepartureRegisterSortedByTime();
    assertTrue(sortedDepartures.get(0).getDepartureTime()
        .isBefore(sortedDepartures.get(1).getDepartureTime()));
  }

  /**
   * Tests that the register can return all departures with the given destination.
   */
  @Test
  void testTrainDepartureByDestination() {
    ArrayList<TrainDeparture> filteredDepartures
        = trainDepartureRegister.getTrainDepartureByDestination("tEsT");
    assertEquals(filteredDepartures.size(), 2);
  }

  /**
   * Tests that the departure register can remove all departures that has passed the current time.
   */
  @Test
  void testRemovePassedDepartures() {
    TrainStationTime trainStationTime = new TrainStationTime();
    trainStationTime.setTrainStationTime("10:00");
    trainDepartureRegister.removePassedDepartures();
    assertNull(trainDepartureRegister.getTrainDepartureByTrainNumber(2));
  }
}