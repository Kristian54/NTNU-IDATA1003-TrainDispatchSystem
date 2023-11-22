package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class TrainDispatchAppUi {
  private TrainDepartureRegister trainDepartureRegister;
  private TrainStationTime trainStationTime;
  // Version
  private static final String version = "v0.5-SNAPSHOT";


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
  private static final int ADD_DELAY_TO_EXISTING_DEPARTURE = 4;
  private static final int ADD_TRACK_TO_EXISTING_DEPARTURE = 5;
  private static final int SEARCH_EXISTING_DEPARTURES_BY_TRAINNUMBER = 6;
  private static final int SEARCH_EXISTING_DEPARTURES_BY_DESTINATION = 7;
  private static final int UPDATE_CLOCK = 8;
  private static final int EXIT_APPLICATION = 9;

  /**
   * Creates an instance of TrainDispatchAppUi.
   */
  public TrainDispatchAppUi() {
  }

  /**
   * Prints the information table.
   */
  private void printInfoTable() {
    System.out.println(TrainStationTime.getTrainStationTime()
        + "   Avganger/" + YELLOW + "Departures  " + COLOR_RESET
        + "Forventet/" + YELLOW + "expected " + COLOR_RESET + "| "
        + "Track/" + YELLOW + "Spor "
        + COLOR_RESET + "|" + " Nummer/" + YELLOW + "Number:" + COLOR_RESET);
    this.printDeparturesSortedByTime();
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  /**
   * Prints the details of one train departure.
   * If destination is longer than 17 characters or train line is longer than 4 characters they will
   * be cut down to prevent stretching.
   * Minimum string length is implemented for all variables for the departure to fit in the
   * info table.
   *
   * @param trainDeparture the train departure to print.
   */
  private void printDeparture(TrainDeparture trainDeparture) {
    String departureTime = String.format("%-6s", trainDeparture.getDepartureTime());
    String trainLine = String.format("%-5s", trainDeparture.getLine());
    String destination = String.format("%-16s", trainDeparture.getDestination());
    String expectedTime = String.format(YELLOW + "%-18s", trainDeparture.getExpectedTime()
        + COLOR_RESET);
    String trackNumber = String.format("%-10s", trainDeparture.getTrackNumber());
    String trainNumber = String.format("%-13s", trainDeparture.getTrainNumber());

    int destinationMaxLeght = 16;
    if (destination.length() > destinationMaxLeght) {
      destination = destination.substring(0, 15) + "..";
    }
    int trainLineMaxLenght = 5;
    if (trainLine.length() > trainLineMaxLenght) {
      trainLine = trainLine.substring(0, trainLineMaxLenght);
    }
    if (trainDeparture.getTrackNumber() == -1) {
      trackNumber = RED + "Invalid   " + COLOR_RESET;
    }
    System.out.println(departureTime + "  " + trainLine + destination
        + expectedTime + "     | " + trackNumber + " | " + trainNumber);
  }

  /**
   * Prints the details of all departures in the train station sorted by time to the console.
   */
  private void printDeparturesSortedByTime() {
    ArrayList<TrainDeparture> sortedDepartures =
        new ArrayList<>(trainDepartureRegister.getDepartureRegisterSortedByTime());

    for (TrainDeparture trainDeparture : sortedDepartures) {
      printDeparture(trainDeparture);
    }
  }

  /**
   * Lets the user add a train departure.
   * Train number must be unique and between 0 and 100. If it
   * isn't unique the train departure will not be added.
   */
  private void userAddDeparture() {
    String patternExample = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    Pattern pattern = Pattern.compile(patternExample);

    System.out.println("Enter the train departure´s destination");
    String trainDestination = getUserString();

    System.out.println("Enter the train departure´s departure time on the format hh:mm");
    String departureTime = getUserString();

    while (!pattern.matcher(departureTime).matches()) {
      System.out.println(RED + "Please make sure the departure "
          + "time is written in correct hh:mm format");
      System.out.println("You entered: " + COLOR_RESET + departureTime);
      System.out.println();
      System.out.println("Please enter a new departure time");
      departureTime = getUserString();
    }

    System.out.println("Enter the train departure´s unique number");
    int trainNumber = getUserInt();

    while (trainNumber < 1 || trainNumber > 99) {
      System.out.println(RED + "Train number must be between 1 and 99" + COLOR_RESET);
      System.out.println();
      System.out.println("Please enter a new unique train number");
      trainNumber = getUserInt();
    }

    System.out.println("Enter the departure´s line");
    String trainLine = getUserString();

    System.out.println("Enter the track where the train will depart from");
    int trainTrack = getUserInt();
    while (trainTrack > 100 || trainTrack < 1) {
      System.out.println(RED + "Track number must be between 1 and 99" + COLOR_RESET);
      System.out.println();
      System.out.println("Please enter a new track");
      trainTrack = getUserInt();
    }

    String amountDelayed = "";

    boolean departureAdded = this.trainDepartureRegister.addDeparture(departureTime, trainLine,
        trainNumber, trainDestination, trainTrack, amountDelayed);


    if (!departureAdded) {
      System.out.println(RED + "Adding train departure failed");
      System.out.println("Please make sure the train number is unique, not assigned to another "
          + "departure and is between 1 and 99" + COLOR_RESET);
    } else {
      System.out.println(GREEN + "Train departure added successfully" + COLOR_RESET);
    }
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
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
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Show info table");
    System.out.println("2. Add a train departure");
    System.out.println("3. Remove a train departure");
    System.out.println("4. Add delay to an existing departure");
    System.out.println("5. Add track to an existing departure");
    System.out.println("6. Search for existing departures by train number");
    System.out.println("7. Search for existing departures by destination");
    System.out.println("8. Update Clock");
    System.out.println("9. Exit application");
  }

  /**
   * TODO Remove or implement.
   */
  private void printEditMessage() {
    System.out.println("Enter the train number of the departure you would like to edit:");
  }

  /**
   * TODO Remove or implement.
   */
  private void printSearchMenu() {
    System.out.println("Please select one of the following choices:");
    System.out.println("1. Search by train number");
    System.out.println("2. Search by destination");
  }

  /**
   * Prints a goodbye message.
   */
  private void printGoodbyeMessage() {
    System.out.println(CYAN + "Goodbye" + COLOR_RESET);
  }

  /**
   * Get´s an int from the user.
   * If the user enters an invalid int -1 will be returned.
   *
   * @return user´s int. If invalid -1 is returned
   */
  private int getUserInt() {
    int inputInt = -1;

    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNextInt()) {
      inputInt = userInput.nextInt();
    }
    return inputInt;
  }

  /**
   * Get´s a string from the user.
   * If no input is given, an empty string will be returned
   *
   * @return inputString user´s string. If empty an empty string will be returned.
   */
  private String getUserString() {
    String inputString = "";

    Scanner userInput = new Scanner(System.in);
    if (userInput.hasNext()) {
      inputString = userInput.next();
    }
    return inputString;
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
   * Lets the user search for all train departures with the given location.
   * If no departures with the given destination is found, the user will be notified.
   */
  private void userSearchByDestination() {
    System.out.println("Enter the destination of the departures you would like to view:");
    ArrayList<TrainDeparture> filteredDepartures =
        this.trainDepartureRegister.getTrainDepartureByDestination(getUserString());
    if (filteredDepartures.isEmpty()) {
      System.out.println(RED + "No departures found" + COLOR_RESET);
    } else {
      System.out.println(TrainStationTime.getTrainStationTime() + "   Avganger/Departures  "
          + "Forventet/expected | " + "Track/Spor | Nummer/Number:");
      for (TrainDeparture trainDeparture : filteredDepartures) {
        printDeparture(trainDeparture);
      }
    }
    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user search for a train departure with the given train number.
   * If no train departure is found with the given train number the user will be notified.
   */
  private void userSearchByTrainNumber() {
    System.out.println("Enter the train number of the departure you would like to view:");
    int trainNumber = getUserInt();

    TrainDeparture departureToPrint =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToPrint != null) {
      printDeparture(departureToPrint);
    } else {
      System.out.println(RED + "Train departure not found" + COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  /**
   * Lets the user add a track number to an existing departure.
   * Track number must be between 1 and 99, if it isn't the user will be notified accordingly.
   */
  private void userAddTrack() {
    System.out.println("Enter the train number of the departure you would like to add a track to:");
    int trainNumber = getUserInt();

    TrainDeparture departureToAddTrack =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    if (departureToAddTrack != null) {
      System.out.println("Please enter a track number between 1 and 99");
      int track = getUserInt();
      if (track < 100 && track > 0) {
        departureToAddTrack.setTrackNumber(track);
        System.out.println(GREEN + "Track added" + COLOR_RESET);
      } else {
        System.out.println(RED + "Track must be between 1 and 99" + COLOR_RESET);
      }
    } else {
      System.out.println(RED + "Train departure not found" + COLOR_RESET);
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user add a delay to an existing departure.
   * If it is not written on the format "hh:mm" or no train departure is found with the given train
   * number the user will be notified accordingly.
   */
  private void userAddDelay() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train number of the departure you would like to add a delay to:");
    int trainNumber = getUserInt();

    TrainDeparture departureToDelay =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);
    if (departureToDelay == null) {
      System.out.println(RED + "Train departure not found" + COLOR_RESET);
    } else {
      System.out.println("Enter the delay time on the format hh:mm:");
      String delay = scanner.next();
      boolean delayAdded = departureToDelay.setDelayTime(delay);
      if (delayAdded) {
        System.out.println(GREEN + "Delay added successfully" + COLOR_RESET);
        scanner.nextLine();
      } else {
        System.out.println(RED + "Delay not added");
        System.out.println("Please make sure the time is written in the format hh:mm");
        System.out.println("You entered: " + COLOR_RESET + delay);
        scanner.nextLine();
      }
    }

    System.out.println();
    System.out.println("Press enter to return to the main menu");
    scanner.nextLine(); // Waits for enter press
  }

  /**
   * Lets the user remove a departure by selecting it´s train number.
   * If no departure is found with the given train number it will notify the user of that and
   * nothing will be removed.
   */
  private void userRemoveDeparture() {
    System.out.println("Enter the train number of the departure you would like to remove:");
    int trainNumber = getUserInt();

    TrainDeparture departureToRemove =
        trainDepartureRegister.getTrainDepartureByTrainNumber(trainNumber);

    boolean trainDepartureRemoved = trainDepartureRegister.removeDeparture(departureToRemove);
    if (trainDepartureRemoved) {
      System.out.println(GREEN + "Train departure removed" + COLOR_RESET);
    } else {
      System.out.println(RED + "Train departure not found");
      System.out.println("You entered: " + COLOR_RESET + trainNumber);
    }

    System.out.println();
    System.out.println("Press enter to return to main menu");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  /**
   * Prepares the application to be run.
   */
  public void init() {
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
      int selectedMenu = getUserInt();

      if (executeMainMenuChoice(selectedMenu) == false) {
        finished = true;
      }
    }
  }
}

