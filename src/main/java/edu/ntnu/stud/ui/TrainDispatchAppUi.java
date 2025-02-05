package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.Scanner;

/**
 * <h5>Represents the UI of the TrainDispatch application.</h5>
 * The UI is a text based menu system.
 *
 * <p>Through the menu, the user should be able to:</p>
 * <ul>
 *   <li>Show info table</li>
 *   <li>Add departure</li>
 *   <li>Remove existing departure</li>
 *   <li>Modify existing departure</li>
 *   <ul>
 *     <li>Update departure time</li>
 *     <li>Update departure destination</li>
 *     <li>Update train line</li>
 *     <li>Add/Update delay</li>
 *     <li>Add/Update track</li>
 *   </ul>
 *   <li>Search for existing departure(s)</li>
 *   <ul>
 *     <li>Search by train number</li>
 *     <li>Search by destination</li>
 *   </ul>
 *   <li>Update clock</li>
 *   <li>Exit application</li>
 *  </ul>
 *
 * @author kristiangarder
 */
public class TrainDispatchAppUi {
  private TrainDepartureRegister trainDepartureRegister;
  private TrainStationTime trainStationTime;
  private UserInputReader userInputReader;
  private UserModifyTrainDeparture userModifyTrainDeparture;
  private UserSearchForTrainDeparture userSearchForTrainDeparture;
  private ShowTrainDepartureInformation showTrainDepartureInformation;
  private UserAddRemoveTrainDeparture userAddRemoveTrainDeparture;

  // Version
  /**
   * Application´s current version
   */
  private static final String version = "v0.7";

  // Color reset
  public static final String COLOR_RESET = "\u001B[0m";
  // Ui Color
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String RED = "\u001B[31m";
  public static final String CYAN = "\u001B[36m";

  // Menu choices:
  private static final int PRINT_INFO_TABLE = 1;
  private static final int ADD_TRAIN_DEPARTURE = 2;
  private static final int REMOVE_EXISTING_DEPARTURE = 3;
  private static final int MODIFY_EXISTING_DEPARTURE = 4;
  private static final int USER_SEARCH_EXISTING_DEPARTURE = 5;
  private static final int UPDATE_CLOCK = 6;
  private static final int EXIT_APPLICATION = 9;

  /**
   * Creates an instance of TrainDispatchAppUi.
   */
  public TrainDispatchAppUi() {
  }

  /**
   * Prints the welcome screen for the application.
   */
  private void printWelcomeScreen() {
    System.out.println(CYAN + "Welcome to trainDispatchApp " + COLOR_RESET + version);
  }

  /**
   * Prints the main menu with choices.
   */
  private void printMainMenu() {
    System.out.println();
    System.out.println();
    System.out.println("Main Menu                                   "
        + TrainStationTime.getTrainStationTime());
    System.out.println("-------------------------------------------------");
    System.out.println("Select one of the following choices:");
    System.out.println("1. Show info table");
    System.out.println("2. Add departure");
    System.out.println("3. Remove existing departure");
    System.out.println("4. Modify existing departure");
    System.out.println("5. Search for existing departure(s)");
    System.out.println("6. Update Clock");
    System.out.println("9. Exit application");
  }

  /**
   * Prints a goodbye message.
   */
  private void printGoodbyeMessage() {
    System.out.println(CYAN + "Goodbye" + COLOR_RESET);
  }

  /**
   * Lets the user set the new system time on the format hh:mm.
   * If entered on incorrect format or is before the current time the user will be notified
   * accordingly.
   */
  public void userUpdateClock() {
    String newTime;
    System.out.println("Enter new time on the format hh:mm:");
    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNext()) {
      newTime = userInput.next();
    } else {
      newTime = "00:00";
    }
    boolean clockUpdated = trainStationTime.setTrainStationTime(newTime);
    if (clockUpdated) {
      System.out.println(GREEN + "Clock updated successfully");
      System.out.println("New time: " + COLOR_RESET + TrainStationTime.getTrainStationTime());
    } else {
      System.out.println(RED + "Clock update failed");
      System.out.println("Please make sure the time is written in the format hh:mm and is after "
          + "the current time");
      System.out.println("You entered: " + COLOR_RESET + newTime);
    }
    userInput.nextLine(); // Consumes the newLine character from the previous input.
    System.out.println();
    System.out.println("Press enter to return to main menu");
    userInput.nextLine(); // Waits for enter press
  }

  /**
   * Executes the selected menu choice by the user.
   *
   * @param selectedMenu users choice
   * @return result true if the application hold, false if it should return to main menu.
   */
  private boolean executeMainMenuChoice(int selectedMenu) {
    boolean result = true;

    switch (selectedMenu) {
      case PRINT_INFO_TABLE:
        this.showTrainDepartureInformation.printInfoTable();
        this.trainDepartureRegister.removePassedDepartures();
        break;

      case ADD_TRAIN_DEPARTURE:
        userAddRemoveTrainDeparture.userAddDeparture();
        break;

      case REMOVE_EXISTING_DEPARTURE:
        userAddRemoveTrainDeparture.userRemoveDeparture();
        break;

      case MODIFY_EXISTING_DEPARTURE:
        TrainDeparture departureToModify = userModifyTrainDeparture.userSelectDeparture();
        if (departureToModify != null) {
          userModifyTrainDeparture.printModifyMenu();
          userModifyTrainDeparture.executeModifyMenuChoice(userInputReader.getUserInt(),
              departureToModify);
        }

        break;

      case USER_SEARCH_EXISTING_DEPARTURE:
        userSearchForTrainDeparture.printSearchMenu();
        userSearchForTrainDeparture.executeSearchMenuChoice(userInputReader.getUserInt());
        break;

      case UPDATE_CLOCK:
        this.userUpdateClock();
        trainDepartureRegister.removePassedDepartures();
        break;

      case EXIT_APPLICATION:
        printGoodbyeMessage();
        result = false;
        break;

      default:
    }

    return result;
  }

  /**
   * Initializes the application.
   *
   * <p>Creates an instance of TrainDepartureRegister</p>
   * <p>Creates an instance of UserModifyTrainDeparture</p>
   * <p>Creates an instance of UserAddRemoveTrainDeparture</p>
   * <p>Creates an instance of UserSearchForTrainDeparture</p>
   * <p>Creates an instance of TrainStationTime and sets the time to "00:00"</p>
   * <p>Creates an instance of UserInputReader</p>
   * <p>Creates an instance of ShowTrainDepartureInformation</p>
   */
  public void init() {
    this.trainDepartureRegister = new TrainDepartureRegister();
    this.userModifyTrainDeparture = new UserModifyTrainDeparture(trainDepartureRegister);
    this.userAddRemoveTrainDeparture = new UserAddRemoveTrainDeparture(trainDepartureRegister);
    this.userSearchForTrainDeparture =
        new UserSearchForTrainDeparture(trainDepartureRegister);
    TrainStationTime trainStationTime = new TrainStationTime();
    this.trainStationTime = new TrainStationTime();
    this.userInputReader = new UserInputReader();
    this.showTrainDepartureInformation =
        new ShowTrainDepartureInformation(this.trainDepartureRegister);
    trainStationTime.setTrainStationTime("00:00");
    //Fills the register with 10 dummy departures. Can be uncommented for testing purposes.
    //trainDepartureRegister.fillTrainStationWithDummyDepartures();
  }

  /**
   * Starts the application. This is the main entrypoint for the instance of TrainDispatchAppUi.
   */
  public void start() {
    this.printWelcomeScreen();

    boolean finished = false;
    while (!finished) {
      printMainMenu();
      int selectedMenu = userInputReader.getUserInt();

      if (!executeMainMenuChoice(selectedMenu)) {
        finished = true;
      }
    }
  }
}

