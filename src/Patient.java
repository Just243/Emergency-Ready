import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single patient in the Emergency Ready waiting list.
 * Stores the patient's ID, name, arrival time, and emergency status.
 *
 * @author Romeo Duarte Infante
 * @version 2026-11-10
 */
public class Patient {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String patientID;
    private final String name;
    private final LocalDateTime arrivalTime;
    private final boolean emergency;

    /**
     * Constructs a patient. The arrival time is set to the current time.
     *
     * @param patientID the unique patient ID
     * @param name the patient's name
     * @param emergency true if the patient is an emergency case
     */
    public Patient(String patientID, String name, boolean emergency) {
        this.patientID = patientID;
        this.name = name;
        this.emergency = emergency;
        this.arrivalTime = LocalDateTime.now();
    }

    /**
     * @return the unique patient ID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @return the patient's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the time the patient arrived
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return true if the patient is an emergency case
     */
    public boolean isEmergency() {
        return emergency;
    }

    /**
     * Returns a readable description of the patient.
     *
     * @return the patient's details as a string
     */
    @Override
    public String toString() {
        return "ID: " + patientID
                + " | Name: " + name
                + " | Arrived: " + arrivalTime.format(TIME_FORMAT)
                + " | Status: " + (emergency ? "EMERGENCY" : "Regular");
    }
}
