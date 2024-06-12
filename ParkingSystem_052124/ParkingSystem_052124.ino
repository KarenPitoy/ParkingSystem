#include <SPI.h>
#include <MFRC522.h>
#include <Servo.h>

#define RST_PIN 9
#define SS_1_PIN 10
#define SS_2_PIN 7
#define Buzzer 2
#define LED_ENTRANCE 3
#define LED_EXIT 4
#define ServoEntrance_PIN 5
#define ServoExit_PIN 6
#define Sensor1 A0 
#define Sensor2 A1 

MFRC522 RFID_Entrance(SS_1_PIN, RST_PIN); // Define RFID reader 1
MFRC522 RFID_Exit(SS_2_PIN, RST_PIN); // Define RFID reader 2
Servo ServoEntrance;
Servo ServoExit;

int angleEntrance = 0; // the current angle of servo motor 1
int angleExit = 0; // the current angle of servo motor 2
unsigned long lastTime = 0;
const unsigned long delayTime = 3000; // 3 seconds
const unsigned long countDelay = 1000; // 1 second
enum ServoState { AT_ANGLE_0, AT_ANGLE_90, COUNTING, DELAY };

ServoState servoState = AT_ANGLE_0;
int messageCount = 0;
unsigned long lastCountTime = 0;

bool prevValue1 = HIGH; 
bool prevValue2 = HIGH; 
bool occupied1 = false; // State memory for sensor 1
bool occupied2 = false; // State memory for sensor 2

int totalVacant = 2; // Total number of vacant sensors, initially set to 2

void setup() {
  Serial.begin(9600);
  SPI.begin();
  pinMode(LED_ENTRANCE, OUTPUT);
  pinMode(LED_EXIT, OUTPUT);
  pinMode(Buzzer, OUTPUT);
  
  RFID_Entrance.PCD_Init(); // Initialize RFID reader 1
  RFID_Exit.PCD_Init(); // Initialize RFID reader 2
  ServoEntrance.attach(ServoEntrance_PIN);
  ServoEntrance.write(angleEntrance);
  ServoExit.attach(ServoExit_PIN);
  ServoExit.write(angleExit);
  
  Serial.println("Scan Card....");
}

void loop() {
  bool value1 = digitalRead(Sensor1);
  bool value2 = digitalRead(Sensor2);
  
  bool changed1 = false; // Flag to track if sensor 1 state changed
  bool changed2 = false; // Flag to track if sensor 2 state changed
  
  if (value1 != prevValue1) { // Check if the state of sensor 1 has changed
    if (value1 == LOW && !occupied1) {
      Serial.println("Sensor1- OCCUPIED");
      occupied1 = true; // Update state memory
      totalVacant--; // Decrement total vacant count
      changed1 = true; // Set flag indicating change
    } else if (value1 == HIGH && occupied1) {
      Serial.println("Sensor1- VACANT");
      occupied1 = false; // Update state memory
      totalVacant++; // Increment total vacant count
      changed1 = true; // Set flag indicating change
    }
    prevValue1 = value1; // Update the previous state of sensor 1
    Serial.print("SensorT: ");
    Serial.println(totalVacant);
  }
  
  if (value2 != prevValue2) { // Check if the state of sensor 2 has changed
    if (value2 == LOW && !occupied2) {
     Serial.println("Sensor2- OCCUPIED");
      occupied2 = true; // Update state memory
      totalVacant--; // Decrement total vacant count
      changed2 = true; // Set flag indicating change
    } else if (value2 == HIGH && occupied2) {
     Serial.println("Sensor2- VACANT");
      occupied2 = false; // Update state memory
      totalVacant++; // Increment total vacant count
      changed2 = true; // Set flag indicating change
    }
    prevValue2 = value2; // Update the previous state of sensor 2
    Serial.print("SensorT: ");
    Serial.println(totalVacant);
  }
  
  // Display current status of both sensors and total vacant count only if there are changes
 if (changed1 || changed2) {
   Serial.print("Sensor1- ");
    Serial.println(occupied1 ? "OCCUPIED" : "VACANT");
    Serial.print("Sensor2- ");
    Serial.println(occupied2 ? "OCCUPIED" : "VACANT");
    Serial.print("SensorT: ");
    Serial.println(totalVacant);
  }
  
  switch (servoState) {
    case AT_ANGLE_0:
      if (RFID_Entrance.PICC_IsNewCardPresent() && RFID_Entrance.PICC_ReadCardSerial()) {
        Serial.println("Entrance Detected");
        bool accessGranted = EntrancehandleRFID(RFID_Entrance, LED_ENTRANCE);
        if (accessGranted) {
          ServoEntrance.write(90); // Set servo 1 to 90 degrees
          servoState = AT_ANGLE_90;
          lastTime = millis(); // Record the current time
        }
      }
      
      if (RFID_Exit.PICC_IsNewCardPresent() && RFID_Exit.PICC_ReadCardSerial()) {
        Serial.println("Exit Detected");
        ExithandleRFID(RFID_Exit, LED_EXIT);
        ServoExit.write(90); // Set servo 2 to 90 degrees
        servoState = AT_ANGLE_90;
        lastTime = millis(); // Record the current time
      }
      break;

    case AT_ANGLE_90:
      // Display message when servo is at 90 degrees
      Serial.println("Servo is at angle 90");
      servoState = COUNTING; // Start counting
      messageCount = 1; // Reset message count
      lastCountTime = millis(); // Record the current time
      break;

    case COUNTING:
      // Count from 1 to 3 with a delay of 1 second between each count
      if (millis() - lastCountTime >= countDelay) {
        Serial.print("Count: ");
        Serial.println(messageCount);
        lastCountTime = millis(); // Record the current time

        // Check if counting is done
        if (messageCount >= 3) {
          servoState = DELAY;
          lastTime = millis(); // Record the current time
        } else {
          messageCount++;
        }
      }
      break;

    case DELAY:
      if (millis() - lastTime >= delayTime) {
        ServoEntrance.write(0); // Set servo 1 back to 0 degrees
        ServoExit.write(0); // Set servo 2 back to 0 degrees
        Serial.println("Rotate Servo Motors to 0°");
        servoState = AT_ANGLE_0;
      }
      break;
  }
  
  delay(100); // Add a small delay for stability
}

bool EntrancehandleRFID(MFRC522 &mfrc522, int ledPin) {
  Serial.print(F("UID Entrance: "));
  tone(Buzzer, 700);
  dump_byte_array(mfrc522.uid.uidByte, mfrc522.uid.size);
  delay(200);
  noTone(Buzzer);
  String ID = "";
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    ID.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? "0" : ""));
    ID.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.println();
  ID.toUpperCase();

  bool accessGranted = false;

  if (ID == "B348600F" || ID=="0314760F") {
    Serial.println("Access Granted");
    accessGranted = true;
  } else {
    Serial.println("Access Denied");
  }

  digitalWrite(ledPin, HIGH);
  delay(3000); // LED stays on for 3 seconds
  digitalWrite(ledPin, LOW);
  
  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();

  return accessGranted;
}

void ExithandleRFID(MFRC522 &mfrc522, int ledPin) {
  Serial.print(F("UID Exit: "));
  tone(Buzzer, 700);
  dump_byte_array(mfrc522.uid.uidByte, mfrc522.uid.size);
  delay(200);
  noTone(Buzzer);
  String ID = "";
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    ID.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? "0" : ""));
    ID.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.println();
  ID.toUpperCase();
  
  digitalWrite(ledPin, HIGH);
  delay(3000); // LED stays on for 3 seconds
  digitalWrite(ledPin, LOW);
  
  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();
}

void dump_byte_array(byte * buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], HEX);
  }
}