import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked deque that manages the patient waiting list.
 * Emergency patients are added to the front, regular patients to the back.
 *
 * @author Romeo Duarte Infante
 * @version 2026-11-10
 */
public class PatientDeque implements Iterable<Patient> {

    /**
     * A node in the doubly linked list.
     */
    private static class Node {
        private Patient patient;
        private Node next;
        private Node prev;

        private Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructs an empty deque.
     */
    public PatientDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a patient to the front of the deque (emergency patients).
     *
     * @param patient the patient to add
     */
    public void addFirst(Patient patient) {
        Node node = new Node(patient);
        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * Adds a patient to the back of the deque (regular patients).
     *
     * @param patient the patient to add
     */
    public void addLast(Patient patient) {
        Node node = new Node(patient);
        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Removes and returns the patient at the front of the deque
     * (the next patient to be called).
     *
     * @return the patient at the front
     * @throws NoSuchElementException if the deque is empty
     */
    public Patient removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The waiting list is empty.");
        }
        Patient patient = head.patient;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        else {
            head.prev = null;
        }
        size--;
        return patient;
    }

    /**
     * Removes and returns the patient at the back of the deque.
     *
     * @return the patient at the back
     * @throws NoSuchElementException if the deque is empty
     */
    public Patient removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The waiting list is empty.");
        }
        Patient patient = tail.patient;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        }
        else {
            tail.next = null;
        }
        size--;
        return patient;
    }

    /**
     * Removes the patient with the given ID from anywhere in the deque.
     *
     * @param patientID the ID of the patient to remove
     * @return true if a patient was removed, false if no match was found
     */
    public boolean removePatient(String patientID) {
        Node current = head;
        while (current != null) {
            if (current.patient.getPatientID().equals(patientID)) {
                if (current == head) {
                    removeFirst();
                }
                else if (current == tail) {
                    removeLast();
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the patient at the front without removing them.
     *
     * @return the front patient, or null if the deque is empty
     */
    public Patient peekFirst() {
        return isEmpty() ? null : head.patient;
    }

    /**
     * @return true if the deque has no patients
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of patients in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the current waiting list to the console.
     */
    public void displayWaitingList() {
        System.out.print(toString());
    }

    /**
     * Returns the waiting list as a numbered, front-to-back string.
     *
     * @return the formatted waiting list
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "The waiting list is empty.\n";
        }
        StringBuilder sb = new StringBuilder();
        int position = 1;
        for (Patient patient : this) {
            sb.append(position++).append(". ").append(patient).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over the patients from front to back.
     *
     * @return a front-to-back iterator
     */
    @Override
    public Iterator<Patient> iterator() {
        return new Iterator<Patient>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Patient next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Patient patient = current.patient;
                current = current.next;
                return patient;
            }
        };
    }
}
