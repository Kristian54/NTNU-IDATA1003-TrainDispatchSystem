package edu.ntnu.stud;

import edu.ntnu.stud.entity.TrainStationTime;
import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 * This is the main class for the TrainDispatch application.
 */
public class TrainDispatchApp {

  public static void main(String[] args) {
    TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
    TrainStationTime trainStationTime = new TrainStationTime();
    trainDispatchAppUi.init();
    trainDispatchAppUi.start();
  }
}
