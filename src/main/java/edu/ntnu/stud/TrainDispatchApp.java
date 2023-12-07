package edu.ntnu.stud;

import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 * This is the main class for the TrainDispatch application.
 */
public class TrainDispatchApp {

  /**
   * This is the method called by the JVM (Java Virtual Machine), or the operating system to
   * start the application. The syntax and name of this method is defined by the system, and
   * all programming languages must implement this method in order for the OS to be able to find
   * which method to call to start the application.
   *
   * This method initializes and starts the application's user interface.
   *
   * @param args command line arguments(currently unused).
   */
  public static void main(String[] args) {
    TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
    trainDispatchAppUi.init();
    trainDispatchAppUi.start();
  }
}
