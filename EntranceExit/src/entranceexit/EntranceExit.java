package entranceexit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

import trial101.Trial101;

public class EntranceExit {
    private static final Logger logger = Logger.getLogger(EntranceExit.class.getName());
    private static final String LOG_FILE = "log.txt";

    public static void main(String[] args) {
        configureLogger();

        // Set the global exception handler
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());

        if (startEntranceExitSystem()) {
            log("Entrance-Exit System build successful");
        } else {
            log("Entrance-Exit System build stopped or unsuccessful");
            return;
        }
    }

    private static boolean startEntranceExitSystem() {
        try {
            // Create an instance of GUI_SENSOR frame
            Frame admin = new Frame();

            // Create an instance of Trial101 and pass the GUI_SENSOR instance to its constructor
            Trial101 trial101 = new Trial101("COM5", admin);
            
           
            // Show the GUI_SENSOR frame
            admin.setVisible(true);
            admin.setLocationRelativeTo(null);

            // Log access to database
            log("Accessing database...");
            // Perform database operations here

            // Log any changes in the code
            log("Code changes detected...");

            return true;
        } catch (Exception e) {
            logError("An error occurred during build", e);
            return false;
        }
    }

    private static void configureLogger() {
        logger.setLevel(Level.ALL);
        try {
            // Create file handler with append mode
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up file handler", e);
        }
    }

    private static void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(new Date());

            // Log the message with left alignment for date and time
            writer.write(String.format("%-25s", dateTime + " - ") + message + "\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error logging message", e);
        }
    }

        private static void logError(String message, Throwable throwable) {
          // Print the error to console
          StringWriter sw = new StringWriter();
          PrintWriter pw = new PrintWriter(sw);
          throwable.printStackTrace(pw);
          System.err.println(sw.toString());

          // Get the first line of the error message
          String errorLine = throwable.toString().split(System.getProperty("line.separator"))[0];

          // Write the error message with aligned date and time to the log file
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String dateTime = dateFormat.format(new Date());

              writer.write(dateTime + " -     " + errorLine + "\n");
          } catch (IOException e) {
              logger.log(Level.SEVERE, "Error logging message", e);
          }
      }

    static class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            logError("Uncaught exception in thread: " + t.getName(), e);
        }
    }
}