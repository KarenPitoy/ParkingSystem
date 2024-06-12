package trial101;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class UIDListener implements SerialPortDataListener {

    private StringBuilder receivedData = new StringBuilder();
    private UIDCallback uidCallback; // Interface to pass UID to GUI

    // Constructor to accept UIDCallback instance
    public UIDListener(UIDCallback callback) {
        this.uidCallback = callback;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
            return;

        byte[] newData = new byte[event.getSerialPort().bytesAvailable()];
        event.getSerialPort().readBytes(newData, newData.length);

        // Append the new data to the receivedData buffer
        receivedData.append(new String(newData));

        // Check if the received data contains the start and end markers for UID
        String data = receivedData.toString();
        int startMarkerIndex = data.indexOf("UID ");
        int endMarkerIndex = data.indexOf("\n", startMarkerIndex);

        if (startMarkerIndex != -1 && endMarkerIndex != -1) {
            // Extract the UID between start and end markers
            String uid = data.substring(startMarkerIndex + 4, endMarkerIndex);
            
            // Notify the UIDCallback interface with the received UID
            uidCallback.onUIDReceived(uid);
            
            // Clear the receivedData buffer to prepare for the next data
            receivedData.setLength(0);
        }
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }
}
