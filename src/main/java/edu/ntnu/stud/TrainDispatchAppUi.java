package edu.ntnu.stud;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

// TODO: Fill in the main method and any other methods you need.

public class TrainDispatchAppUi {
  private TrainStation trainStation;

  /**
   * Creates an instance of TrainDispatchApp.
   */
  public TrainDispatchAppUi() {
    this.trainStation = new TrainStation();
  }

  /**
   * Prints the information table.
   */
  public void printInfoTable() {
    System.out.println(trainStation.getGlobalTime() + "   Avganger/Departures   " +
        "Forventet/expected | "
        + "Track/Spor | Nummer/Number:");
    trainStation.printDeparturesSortedByTime();
  }

  /**
   * NOT COMPLETE!
   * Lets a user add a train departure
   */
  private void userAddStudent() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the train destination:");
    String trainDestination = scanner.nextLine();
    System.out.println("Enter the time of the departure on the format hh:mm :");
    String departureTime = scanner.nextLine();
    System.out.println("Enter the train line:");
    String trainLine = scanner.nextLine();
    System.out.println("Enter the train number:");
    int number = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter the track number:");
    String trackNum = scanner.nextLine();
    System.out.println("If there is a delay, enter it on the format hh:mm :");
    String delay = scanner.nextLine();

    this.trainStation.addDeparture(departureTime, trainLine, number, trainDestination, trackNum, delay);

  }

  private int displayMenu() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Main Menu:");
    System.out.println("1. Enter new sighting");
    int option = scanner.nextInt();
    return option;
  }

  /**
   * Start method where the application will be run.
   */
  public void start() {
    trainStation.setGlobalTime("23:00");
    trainStation.fillTrainStationWithDummyDepartures();
    printInfoTable();
    //boolean finished = false;
    //printInfoTable();
    //while (!finished)
    //userAddStudent();
    //printInfoTable();
    }
  }

