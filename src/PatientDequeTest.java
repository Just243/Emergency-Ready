
import student.TestCase;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tests the methods in the PatientDeque class.
 *
 * @author Noah Rakvica
 * @version 2026-11-20
 */
public class PatientDequeTest extends TestCase {

    private PatientDeque deque;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;

    /**
     * Sets up the test cases.
     */
    public void setUp() {
        deque = new PatientDeque();
        patient1 = new Patient("P001", "John Smith", true);
        patient2 = new Patient("P002", "Jane Doe", false);
        patient3 = new Patient("P003", "Mike Jones", false);
    }

    /**
     * Tests the constructor and empty deque.
     */
    public void testConstructor() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());
    }

    /**
     * Tests adding patients to the front.
     */
    public void testAddFirst() {
        deque.addFirst(patient1);
        deque.addFirst(patient2);

        assertEquals(2, deque.size());
        assertSame(patient2, deque.peekFirst());
    }

    /**
     * Tests adding patients to the back.
     */
    public void testAddLast() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        assertEquals(2, deque.size());
        assertSame(patient1, deque.peekFirst());
    }

    /**
     * Tests adding patients to both ends.
     */
    public void testAddBothEnds() {
        deque.addLast(patient1);
        deque.addLast(patient2);
        deque.addFirst(patient3);

        assertEquals(3, deque.size());
        assertSame(patient3, deque.peekFirst());
    }

    /**
     * Tests removing the first patient.
     */
    public void testRemoveFirst() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        assertSame(patient1, deque.removeFirst());
        assertEquals(1, deque.size());
        assertSame(patient2, deque.peekFirst());
    }

    /**
     * Tests removing the last patient.
     */
    public void testRemoveLast() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        assertSame(patient2, deque.removeLast());
        assertEquals(1, deque.size());
        assertSame(patient1, deque.peekFirst());
    }

    /**
     * Tests removing the only patient.
     */
    public void testRemoveOnlyPatient() {
        deque.addFirst(patient1);

        assertSame(patient1, deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());

        deque.addFirst(patient2);

        assertSame(patient2, deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    /**
     * Tests removing from an empty deque.
     */
    public void testRemoveFromEmpty() {
        Exception firstException = null;
        try {
            deque.removeFirst();
        }
        catch (NoSuchElementException e) {
            firstException = e;
        }
        assertNotNull(firstException);

        Exception lastException = null;
        try {
            deque.removeLast();
        }
        catch (NoSuchElementException e) {
            lastException = e;
        }
        assertNotNull(lastException);
    }

    /**
     * Tests removing a patient from the front by ID.
     */
    public void testRemovePatientFront() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        assertTrue(deque.removePatient("P001"));
        assertEquals(1, deque.size());
        assertSame(patient2, deque.peekFirst());
    }

    /**
     * Tests removing a patient from the back by ID.
     */
    public void testRemovePatientBack() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        assertTrue(deque.removePatient("P002"));
        assertEquals(1, deque.size());
        assertSame(patient1, deque.peekFirst());
    }

    /**
     * Tests removing a patient from the middle by ID.
     */
    public void testRemovePatientMiddle() {
        deque.addLast(patient1);
        deque.addLast(patient2);
        deque.addLast(patient3);

        assertTrue(deque.removePatient("P002"));
        assertEquals(2, deque.size());
        assertSame(patient1, deque.peekFirst());

        assertSame(patient3, deque.removeLast());
    }

    /**
     * Tests removing an ID that does not exist.
     */
    public void testRemovePatientNotFound() {
        deque.addLast(patient1);

        assertFalse(deque.removePatient("P999"));
        assertEquals(1, deque.size());
        assertSame(patient1, deque.peekFirst());
    }

    /**
     * Tests peekFirst without removing the patient.
     */
    public void testPeekFirst() {
        deque.addLast(patient1);

        assertSame(patient1, deque.peekFirst());
        assertEquals(1, deque.size());
    }

    /**
     * Tests the iterator.
     */
    public void testIterator() {
        deque.addLast(patient1);
        deque.addLast(patient2);
        deque.addLast(patient3);

        Iterator<Patient> iterator = deque.iterator();

        assertTrue(iterator.hasNext());
        assertSame(patient1, iterator.next());

        assertTrue(iterator.hasNext());
        assertSame(patient2, iterator.next());

        assertTrue(iterator.hasNext());
        assertSame(patient3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    /**
     * Tests calling next on an exhausted iterator.
     */
    public void testIteratorNextWhenEmpty() {
        Iterator<Patient> iterator = deque.iterator();

        assertFalse(iterator.hasNext());

        Exception exception = null;
        try {
            iterator.next();
        }
        catch (NoSuchElementException e) {
            exception = e;
        }

        assertNotNull(exception);
    }

    /**
     * Tests the toString method on an empty deque.
     */
    public void testToStringEmpty() {
        assertEquals("The waiting list is empty.\n", deque.toString());
    }

    /**
     * Tests the toString method with patients.
     */
    public void testToStringWithPatients() {
        deque.addLast(patient1);
        deque.addLast(patient2);

        String result = deque.toString();

        assertTrue(result.contains("1. ID: P001"));
        assertTrue(result.contains("2. ID: P002"));
        assertTrue(result.contains("Status: EMERGENCY"));
        assertTrue(result.contains("Status: Regular"));
    }
}
