package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import edu.ntnu.stud.entity.TrainStationTime;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Fill in the main method and any other methods you need.

public class TrainDispatchAppUi {
  private TrainDepartureRegister trainStation;
  private TrainStationTime trainStationTime;
  // Version
  private static final String version = "v0.2-SNAPSHOT";

  // Menu choices:
  private static final int PRINT_INFO_TABLE = 1;
  private static final int ADD_TRAIN_DEPARTURE = 2;
  private static final int EDIT_EXISTING_DEPARTURE = 3;
  private static final int SEARCH_EXISTING_DEPARTURES = 4;
  private static final int UPDATE_CLOCK = 5;
  private static final int EXIT_APPLICATION = 9;

  /**
   * Creates an instance of TrainDispatchApp.
   */
  public TrainDispatchAppUi() {
    this.trainStation = new TrainDepartureRegister();
    this.trainStationTime = new TrainStationTime();
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
    System.out.println(trainStationTime.getTrainStationTime() + "   Avganger/Departures   " +
        "Forventet/expected | "
        + "Track/Spor | Nummer/Number:");
    this.printDeparturesSortedByTime();
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
        new ArrayList<>(trainStation.getSortedDepartureRegister());

    for (TrainDeparture trainDeparture : sortedDepartures) {
      printDeparture(trainDeparture);
    }
  }

  /**
   * Parts of the trainNumber validation was suggested by ChatGPT.
   * Lets a user add a train departure. Train number must be unique and between 0 and 99. If it
   * isn't unique the train departure will not be added.
   */
  private void userAddStudent() {
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

    boolean trainDepartureAdded = this.trainStation.addDeparture(departureTime, trainLine, number,
        trainDestination, trackNum, delay);
    if (trainDepartureAdded == true) {
      System.out.println("Train departure added");
    } else {
      System.out.println("Train number must be unique. Train departure not added");
      System.out.println("");
    }
  }

  /**
   * Prints the welcome screen for the application
   */
  public void printWelcomeScreen() {
    System.out.println("Welcome to trainDispatchApp" + version);
  }

  /**
   * Prints the main menu with choices
   */
  public void printMainMenu() {
    System.out.println("");
    System.out.println("-------------------------------------------");
    System.out.println("Main Menu                             "+trainStationTime.getTrainStationTime());
    System.out.println("-------------------------------------------");
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Print info table");
    System.out.println("2. Add a train departure");
    System.out.println("3. Edit an existing departure");
    System.out.println("4. Search for existing departures");
    System.out.println("5. Update Clock");
    System.out.println("9. Exit application");

  }

  /**
   * Prints a menu with different choices to edit an existing departure
   */
  public void printEditMenu() {
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Add track");
    System.out.println("2. Add delay");
  }

  public void printEditMessage() {
    System.out.println("Enter the train number of the departure you would like to edit:");
  }

  public void printSearchMenu() {
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Search by train number");
    System.out.println("2. Search by destination");
  }

  /**
   * Prints a goodbye message
   */
  public void printGoodbyeMessage() {
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
  public void userSetGlobalTime() {
    String newTime;
    System.out.println("Enter new time on the format hh:mm:");
    Scanner userInput = new Scanner(System.in);
    if(userInput.hasNext()) {
      newTime = userInput.next();
    } else {
      newTime = "00:00";
    }
    boolean clockUpdated = trainStationTime.setTrainStationTime(newTime);
    if (clockUpdated) {
      System.out.println("Clock updates successfully");
      System.out.println("New time: " + trainStationTime.getTrainStationTime());
    }
    else {
      System.out.println("Clock update failed");
      System.out.println("Please make sure the time is written in the format hh:mm and is after the current time");
      System.out.println("You entered: " + newTime);
    }
  }

  /**
   * Executes the selected menu choice by the user.
   * // TODO: Finish java doc
   *
   * @param selectedMenu
   * @return
   */
  public boolean executeMainMenuChoice(int selectedMenu) {
    boolean result = true;
    Scanner scanner = new Scanner(System.in);

    switch(selectedMenu) {
      case PRINT_INFO_TABLE:
        trainStation.removePassedDepartures();
        this.printInfoTable();

        break;

      case ADD_TRAIN_DEPARTURE:
        this.userAddStudent();
        break;

      case EDIT_EXISTING_DEPARTURE:
        this.printEditMessage();
        break;

      case SEARCH_EXISTING_DEPARTURES:
        this.printSearchMenu();
        break;

      case UPDATE_CLOCK:
        this.userSetGlobalTime();
        break;

      case EXIT_APPLICATION:
        printGoodbyeMessage();
        result = false;
        break;
    }
    return result;
  }

  public void init() {
    //TODO: Implement method
    TrainStationTime trainStationTime = new TrainStationTime();
  }

  /**
   * Start method where the application will be run.
   */
  public void start() {
    trainStation.fillTrainStationWithDummyDepartures();
    trainStationTime.setTrainStationTime("00:00");
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

