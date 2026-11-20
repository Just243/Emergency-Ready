
import student.TestCase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tests the methods in the Patient class.
 *
 * @author Noah Rakvica
 * @version 2026-11-20
 */
public class PatientTest extends TestCase {

    private Patient emergencyPatient;
    private Patient regularPatient;

    /**
     * Sets up the test cases.
     */
    public void setUp() {
        emergencyPatient = new Patient("P001", "John Smith", true);
        regularPatient = new Patient("P002", "Jane Doe", false);
    }

    /**
     * Tests getPatientID().
     */
    public void testGetPatientID() {
        assertEquals("P001", emergencyPatient.getPatientID());
        assertEquals("P002", regularPatient.getPatientID());
    }

    /**
     * Tests getName().
     */
    public void testGetName() {
        assertEquals("John Smith", emergencyPatient.getName());
        assertEquals("Jane Doe", regularPatient.getName());
    }

    /**
     * Tests getArrivalTime().
     */
    public void testGetArrivalTime() {
        LocalDateTime arrival = emergencyPatient.getArrivalTime();

        assertNotNull(arrival);
        assertTrue(!arrival.isAfter(LocalDateTime.now()));
    }

    /**
     * Tests isEmergency().
     */
    public void testIsEmergency() {
        assertTrue(emergencyPatient.isEmergency());
        assertFalse(regularPatient.isEmergency());
    }

    /**
     * Tests the toString() method for an emergency patient.
     */
    public void testToStringEmergency() {
        String result = emergencyPatient.toString();

        assertTrue(result.contains("ID: P001"));
        assertTrue(result.contains("Name: John Smith"));
        assertTrue(result.contains("Status: EMERGENCY"));
    }

    /**
     * Tests the toString() method for a regular patient.
     */
    public void testToStringRegular() {
        String result = regularPatient.toString();

        assertTrue(result.contains("ID: P002"));
        assertTrue(result.contains("Name: Jane Doe"));
        assertTrue(result.contains("Status: Regular"));
    }
}
