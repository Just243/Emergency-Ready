import java.lang.reflect.Field;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the EmergencyReady controller.
 * Verifies patient management operations and waiting list behavior.
 *
 * @author Group 53
 * @version 2026-11-10
 */
public class EmergencyReadyTest {

    private EmergencyReady app;
    private PatientDeque waitingList;

    /**
     * Initializes the application before each test.
     *
     * @throws Exception
     *             if the waiting list field cannot be accessed
     */
    public void setUp() throws Exception {
        app = new EmergencyReady();

        Field field = EmergencyReady.class.getDeclaredField("waitingList");

        field.setAccessible(true);

        waitingList = (PatientDeque)field.get(app);
    }


    /**
     * Sets simulated user input for testing.
     *
     * @param input
     *            the input to simulate
     */
    private void setInput(String input) {
        InputHandler.setScanner(new Scanner(new ByteArrayInputStream(input
            .getBytes())));
    }


    /**
     * Tests adding an emergency patient.
     */
    public void testAddEmergencyPatient() {
        setInput("E001\nJohn\n");

        app.addEmergencyPatient();

        assertEquals(1, waitingList.size());
        assertTrue(waitingList.peekFirst().isEmergency());
        assertEquals("John", waitingList.peekFirst().getName());
    }


    /**
     * Tests adding a regular patient.
     */
    public void testAddRegularPatient() {
        setInput("R001\nJane\n");

        app.addRegularPatient();

        assertEquals(1, waitingList.size());
        assertFalse(waitingList.peekFirst().isEmergency());
        assertEquals("Jane", waitingList.peekFirst().getName());
    }


    /**
     * Tests removing a patient by ID.
     */
    public void testRemovePatient() {
        waitingList.addLast(new Patient("P001", "John", false));

        setInput("P001\n");

        app.removePatient();

        assertTrue(waitingList.isEmpty());
    }


    /**
     * Tests removing a patient that does not exist.
     */
    public void testRemovePatientNotFound() {
        waitingList.addLast(new Patient("P001", "John", false));

        setInput("P999\n");

        app.removePatient();

        assertEquals(1, waitingList.size());
    }


    /**
     * Tests calling the next patient.
     */
    public void testCallNextPatient() {
        waitingList.addLast(new Patient("P001", "John", false));

        app.callNextPatient();

        assertTrue(waitingList.isEmpty());
    }


    /**
     * Tests calling the next patient when the waiting list is empty.
     */
    public void testCallNextPatientEmpty() {
        app.callNextPatient();

        assertTrue(waitingList.isEmpty());
    }


    /**
     * Tests displaying the waiting list.
     */
    public void testDisplayWaitingList() {
        waitingList.addLast(new Patient("P001", "John", false));

        app.displayWaitingList();

        assertEquals(1, waitingList.size());
    }
}
