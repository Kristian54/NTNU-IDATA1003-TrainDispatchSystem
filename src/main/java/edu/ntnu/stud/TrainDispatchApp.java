package edu.ntnu.stud;

import edu.ntnu.stud.ui.TrainDispatchAppUi;

/**
 * <h3>This is the main class for the TrainDispatch application.</h3>
 */
public class TrainDispatchApp {

  /**
   * This is the method called by the JVM (Java Virtual Machine), or the operating system to
   * start the application. The syntax and name of this method is defined by the system, and
   * all programming languages must implement this method in order for the OS to be able to find
   * which method to call for starting the application.
   * <p>
   * This method initializes and starts the application's user interface.
   * </p>
   *
   * @param args command line arguments(currently unused).
   */
  public static void main(String[] args) {
    TrainDispatchAppUi trainDispatchAppUi = new TrainDispatchAppUi();
    trainDispatchAppUi.init();
    trainDispatchAppUi.start();
  }
}
