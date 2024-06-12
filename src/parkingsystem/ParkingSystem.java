package parkingsystem;

import javax.swing.JFrame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

import trial101.Trial101;

public class ParkingSystem {
    private static final Logger logger = Logger.getLogger(ParkingSystem.class.getName());
    private static final String LOG_FILE = "log.txt";
    private static Admin admin;

    public static void main(String[] args) {
        // Configure logger
        configureLogger();

        // Set the global exception handler
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());

        if (startParkingSystem()) {
            log("Parking System build successful");
        } else {
            log("Parking System build stopped or unsuccessful");
            return;
        }

        // Start the COM port monitoring in a new thread
       
    }

    private static boolean startParkingSystem() {
        try {
            // Create an instance of Admin frame
            admin = new Admin();

            // Create an instance of Trial101 and pass the Admin instance to its constructor
            Trial101 trial101 = new Trial101("COM5", admin);

            // Show the Admin frame
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
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(LOG_FILE, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
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
