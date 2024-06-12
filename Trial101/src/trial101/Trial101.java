package trial101;

import com.fazecast.jSerialComm.SerialPort;

public class Trial101 {

    private SerialPort serialPort;
    private UIDListener uidListener;
    private UIDCallback uidCallback; // Interface to pass UID to GUI

    // Constructor
    public Trial101(String portName, UIDCallback callback) {
        this.uidCallback = callback; // Store the callback
        // Get the specified COM port
        serialPort = SerialPort.getCommPort(portName);
        
        // Open the COM port
        boolean hasOpened = serialPort.openPort();
        if (!hasOpened) {
            throw new IllegalStateException("Failed to open serial port");
        }

        // Add a shutdown hook to close the COM port when the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            serialPort.closePort();
        }));

        // Create a UIDListener instance
        uidListener = new UIDListener(uidCallback);
        
        // Add the UIDListener to the serial port
        serialPort.addDataListener(uidListener);

        // Check if the COM port has an Arduino connected
        boolean arduinoDetected = serialPort.isOpen(); // Check if the port is open, not opening it again
        if (arduinoDetected) {
            System.out.println("Arduino detected on " + portName);
            
        } else {
            System.out.println("No Arduino detected on " + portName);
            // Add a thread to continuously check if the port is still open
           
        }
    }
      public static boolean isPortAvailable(String portName) {
        SerialPort serialPort = SerialPort.getCommPort(portName);
        return serialPort.isOpen();
    }
}
