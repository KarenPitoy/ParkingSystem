package entranceexit;

import java.util.concurrent.TimeUnit;

public class SerialPortMonitor {
    private final SerialPortStatusListener listener;
    private final String portName;
    private boolean isMonitoring;
    private Thread monitorThread;

    public SerialPortMonitor(SerialPortStatusListener listener, String portName) {
        this.listener = listener;
        this.portName = portName;
    }

    public void startMonitoring() {
        if (!isMonitoring) {
            isMonitoring = true;
            monitorThread = new Thread(this::monitorSerialPort);
            monitorThread.start();
        }
    }

    public void stopMonitoring() {
        if (isMonitoring) {
            isMonitoring = false;
            try {
                monitorThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void monitorSerialPort() {
        while (isMonitoring) {
            // Check the status of the serial port here (you need to implement this logic)
            boolean isConnected = true; // Example: Replace with actual check
            listener.onSerialPortStatusChanged(isConnected);

            // Sleep for a short interval before checking again
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
