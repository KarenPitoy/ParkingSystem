package parkingsystem;

import java.util.Random;

public class SerialMonitor implements Runnable {
    private final String portName;
    private final SerialConnectionListener listener;
    private boolean connected;

    public SerialMonitor(String portName, SerialConnectionListener listener) {
        this.portName = portName;
        this.listener = listener;
        this.connected = true; // Initially assume it's connected
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(5000); // Check every 5 seconds

                // Simulate connection loss and restoration
                if (random.nextBoolean()) {
                    if (connected) {
                        connected = false;
                        listener.onConnectionLost();
                    } else {
                        connected = true;
                        listener.onConnectionRestored();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
