package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainDispatchAppUi {
  private TrainDepartureRegister trainDepartureRegister;
  private TrainStationTime trainStationTime;
  // Version
  private static final String version = "v0.3-SNAPSHOT";

  // Menu choices:
  private static final int PRINT_INFO_TABLE = 1;
  private static final int ADD_TRAIN_DEPARTURE = 2;
  private static final int REMOVE_EXISTING_DEPARTURE = 3;
  private static final int ADD_DELAY_TO_EXISTING_DEPARTURE = 4;
  private static final int ADD_TRACK_TO_EXISTING_DEPARTURE = 5;
  private static final int SEARCH_EXISTING_DEPARTURES_BY_TRAINNUMBER = 6;
  private static final int SEARCH_EXISTING_DEPARTURES_BY_DESTINATION = 7;
  private static final int UPDATE_CLOCK = 8;
  private static final int EXIT_APPLICATION = 9;

  /**
   * Creates an instance of TrainDispatchApp.
   */
  public TrainDispatchAppUi() {
  }

  /**
   * Prints the global time.
   */
  public void printGlobalTime() {
    System.out.println("Global time: " + trainStationTime.getTrainStationTime());
  }

  /**
   * Prints the information table.
   */
  public void printInfoTable() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(trainStationTime.getTrainStationTime() + "   Avganger/Departures   "
        + "Forventet/expected | "
        + "Track/Spor | Nummer/Number:");
    this.printDeparturesSortedByTime();
    System.out.println("Press enter to return to main menu");
    scanner.nextLine();
  }

  /**
   * Prints the details of one train departure.
   *
   * @param trainDeparture the train departure to print.
   */
  public void printDeparture(TrainDeparture trainDeparture) {
    String departureTime = String.format("%-8s", trainDeparture.getTime())
        + String.format("%-5s", trainDeparture.getLine())
        + String.format("%-15s", trainDeparture.getDestination());
    String line = String.format("%-18s", trainDeparture.getExpectedTime());
    String trackNumber = String.format("%-10s", trainDeparture.getTrackNumber());
    String destination = String.format("%-13s", trainDeparture.getTrainNumber());
    System.out.println(departureTime + "  "
        + line + " | " + trackNumber + " | " + destination);
  }

  /**
   * Prints the details of all departures in the train station sorted by time to the console.
   */
  public void printDeparturesSortedByTime() {
    ArrayList<TrainDeparture> sortedDepartures =
        new ArrayList<>(trainDepartureRegister.getDepartureRegisterSortedByTime());

    for (TrainDeparture trainDeparture : sortedDepartures) {
      printDeparture(trainDeparture);
    }
  }

  /**
   * Parts of the trainNumber validation was suggested by ChatGPT.
   * Lets a user add a train departure. Train number must be unique and between 0 and 99. If it
   * isn't unique the train departure will not be added.
   */
  private void userAddDeparture() {
    // Creates an instance of scanner
    Scanner scanner = new Scanner(System.in);
    // Asks the user for train destination
    System.out.println("Enter the train destination:");
    String trainDestination = scanner.nextLine();
    // Asks the user for the time of the departure
    System.out.println("Enter the time of the departure on the format hh:mm :");
    String departureTime = scanner.nextLine();
    // Asks the user for the train line
    System.out.println("Enter the train line:");
    String trainLine = scanner.nextLine();
    // Asks the user for train number. Must be an int between 0 and 99 or it will not be accepted
    int number = 0; // Initialize train number as 0
    boolean validInput = false; // Initialize validInput as false
    // While valid input is false, keep trying to get a valid input from the user
    while (!validInput) {
      try {
        System.out.println("Enter the train number:");
        int inputNumber = Integer.parseInt(scanner.nextLine());
        if (inputNumber >= 0 && inputNumber <= 99) {
          number = inputNumber;
          validInput = true; // Set validInput to true if parsing and validation succeed
        } else {
          System.out.println("Train number must be between 0 and 99");
          System.out.println("You entered: " + inputNumber);
        }

      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Train number must be unique and between 0 and 99");
      }
    }
    System.out.println("Enter the track number:");
    String trackNum = scanner.nextLine();
    System.out.println("If there is a delay, enter it on the format hh:mm :");
    String delay = scanner.nextLine();

    boolean trainDepartureAdded = this.trainDepartureRegister.addDeparture(departureTime, trainLine,
        number, trainDestination, trackNum, delay);
    if (trainDepartureAdded == true) {
      System.out.println("Train departure added");
    } else {
      System.out.println("Train number must be unique. Train departure not added");
      System.out.println("");
    }
    System.out.println("Press enter to return to main menu");
    scanner.nextLine();
  }

  /**
   * Prints the welcome screen for the application.
   */
  public void printWelcomeScreen() {
    System.out.println("Welcome to trainDispatchApp" + version);
  }

  /**
   * Prints the main menu with choices.
   */
  public void printMainMenu() {
    System.out.println("");
    System.out.println("-------------------------------------------");
    System.out.println("Main Menu                             "
        + trainStationTime.getTrainStationTime());
    System.out.println("-------------------------------------------");
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Print info table");
    System.out.println("2. Add a train departure");
    System.out.println("3. Remove a train departure");
    System.out.println("4. Add delay to an existing departure");
    System.out.println("5. Add a track to an existing departure");
    System.out.println("6. Search for existing departures by train number");
    System.out.println("7. Search for existing departures by destination");
    System.out.println("8. Update Clock");
    System.out.println("9. Exit application");
  }

  /**
   * TODO Remove or implement.
   */
  public void printEditMessage() {
    System.out.println("Enter the train number of the departure you would like to edit:");
  }

  /**
   * TODO Remove or implement.
   */
  public void printSearchMenu() {
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Search by train number");
    System.out.println("2. Search by destination");
  }

  /**
   * Prints a goodbye message.
   */
  public void printGoodbyeMessage() {
    //TODO: Finish message.
    System.out.println("Thank you for using this fantastic A ;) application, goodbye.");
  }


  /**
   * Gets input from the user in main menu and verifies that the input is valid.
   *
   * @return the menu choice by the user
   */
  public int getUserMenuChoice() {
    int selectedMenu = -1;

    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNextInt()) {
      selectedMenu = userInput.nextInt();
    } else {
      selectedMenu = -1;
    }
    return selectedMenu;
  }

  /**
   * Lets the user set the new system time on the format hh:mm.
   * TODO: Finish java doc
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
      System.out.println("Clock updates successfully");
      System.out.println("New time: " + trainStationTime.getTrainStationTime());
    } else {
      System.out.println("Clock update failed");
      System.out.println("Please make sure the time is written in the format hh:mm and is after "
          + "the current time");
      System.out.println("You entered: " + newTime);
    }
    userInput.nextLine();
    System.out.println("Press enter to return to main menu");
    userInput.nextLine();
  }

  /**
   * Executes the selected menu choice by the user.
   * // TODO: Finish java doc
   *
   * @return result the users choice
   */
  public boolean executeMainMenuChoice(int selectedMenu) {
    boolean result = true;
    Scanner scanner = new Scanner(System.in);

    switch (selectedMenu) {
      case PRINT_INFO_TABLE:
        trainDepartureRegister.removePassedDepartures();
        this.printInfoTable();

        break;

      case ADD_TRAIN_DEPARTURE:
        this.userAddDeparture();
        break;

      case REMOVE_EXISTING_DEPARTURE:
        this.userRemoveDeparture();
        break;

      case ADD_DELAY_TO_EXISTING_DEPARTURE:
        this.userAddDelay();
        break;

      case ADD_TRACK_TO_EXISTING_DEPARTURE:
        this.userAddTrack();
        break;

      case SEARCH_EXISTING_DEPARTURES_BY_TRAINNUMBER:
        this.userSearchByTrainNumber();
        break;

      case SEARCH_EXISTING_DEPARTURES_BY_DESTINATION:
        this.userSearchByDestination();
        break;

      case UPDATE_CLOCK:
        this.userUpdateClock();
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
   * Lets the user search for all train departures with the given location
   */
  private void userSearchByDestination() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the destination of the departures you would like to view:");
    ArrayList<TrainDeparture> filteredDepartures = this.trainDepartureRegister.getTrainDepartureByDestination(scanner.nextLine());
    System.out.println(trainStationTime.getTrainStationTime() + "   Avganger/Departures   "
        + "Forventet/expected | " + "Track/Spor | Nummer/Number:");
    for (TrainDeparture trainDeparture : filteredDepartures) {
      printDeparture(trainDeparture);
    }
    System.out.println("Press enter to return to main menu");
    scanner.nextLine();
  }

  /**
   * Lets the user search for a train departure with the given train number.
   */
  private void userSearchByTrainNumber() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to view:");
    int trainNumber = -1;
    if (scanner.hasNextInt()) {
      trainNumber = scanner.nextInt();
    } else {
      trainNumber = -1;
    }
    TrainDeparture departureToPrint =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToPrint != null) {
      printDeparture(departureToPrint);
    } else {
      System.out.println("Train departure not found");
    }

    scanner.nextLine();
    System.out.println("Press enter to return to main menu");
    scanner.nextLine();
  }

  /**
   * TODO: Finish java doc.
   */
  private void userAddTrack() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to add a track to:");
    int trainNumber = -1;
    if (scanner.hasNextInt()) {
      trainNumber = scanner.nextInt();
    } else {
      trainNumber = -1;
    }
    TrainDeparture departureToAddTrack =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToAddTrack != null) {
      System.out.println("Please enter the track:");
      String track = scanner.next();
      departureToAddTrack.setTrackNumber(track);
    } else {
      System.out.println("Train departure not found");
    }
    System.out.println("Track added");
    scanner.nextLine(); // Consume the newline character from the previous input

    // Wait for Enter key press
    System.out.println("Press enter to return to the main menu");
    scanner.nextLine(); // This will wait until the user presses Enter
  }

  /**
   * //TODO: Finish java doc.
   */
  private void userAddDelay() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to add a delay to:");
    int trainNumber = -1;
    if (scanner.hasNextInt()) {
      trainNumber = scanner.nextInt();
    } else {
      trainNumber = -1;
    }
    TrainDeparture departureToDelay =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);
    if (departureToDelay == null) {
        System.out.println("Train departure not found");
    } else {
      System.out.println("Enter the delay time on the format hh:mm:");
      String delay = scanner.next();
      boolean delayAdded = departureToDelay.setDelayTime(delay);
      if (delayAdded) {
        System.out.println("Delay added successfully");
      } else {
        System.out.println("Delay not added");
        System.out.println("Please make sure the time is written in the format hh:mm");
        System.out.println("You entered: " + delay);
      }
    }
    scanner.nextLine(); // Consume the newline character from the previous input

    // Wait for Enter key press
    System.out.println("Press enter to return to the main menu");
    scanner.nextLine(); // This will wait until the user presses Enter
  }

  /**
   * TODO: FINISH JAVA DOC
   */
  private void userRemoveDeparture() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to remove:");
    int trainNumber = -1;
    if (scanner.hasNextInt()) {
      trainNumber = scanner.nextInt();
    } else {
      trainNumber = -1;
    }
    TrainDeparture departureToRemove =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    boolean trainDepartureRemoved = trainDepartureRegister.removeDeparture(departureToRemove);
    if (trainDepartureRemoved) {
      System.out.println("Train departure removed");
    } else {
      System.out.println("Train departure not found");
      System.out.println("You entered:" + trainNumber);
    }
    scanner.nextLine();
    System.out.println("Press enter to return to main menu");
    scanner.nextLine();
  }

  public void init() {
    //TODO: Implement method
    TrainStationTime trainStationTime = new TrainStationTime();
    this.trainDepartureRegister = new TrainDepartureRegister();
    this.trainStationTime = new TrainStationTime();
    trainStationTime.setTrainStationTime("00:00");
    trainDepartureRegister.fillTrainStationWithDummyDepartures();
  }

  /**
   * Start method where the application will be run.
   */
  public void start() {
    this.printWelcomeScreen();

    boolean finished = false;
    while (!finished) {
      printMainMenu();
      int selectedMenu = getUserMenuChoice();

      if (executeMainMenuChoice(selectedMenu) == false) {
        finished = true;
      }
    }
  }
}

