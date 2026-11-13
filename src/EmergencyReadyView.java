
import java.util.Scanner;

/**
 * Handles displaying information to the user. This class is responsible for the
 * view in MVC.
 * 
 * @author Maddie
 * @version 2026-11-12
 */
public class EmergencyReadyView
{

    private Scanner scanner;

    /**
     * Constructs the EmergencyReadyView.
     */
    public EmergencyReadyView()
    {
        scanner = new Scanner(System.in);
    }


    /**
     * Displays the login menu.
     */
    public void displayLoginMenu()
    {
        System.out.println("\n===== Emergency Ready Login =====");
        System.out.println("Please enter your hospital credentials.");
    }


    /**
     * Displays the main application menu.
     */
    public void displayMainMenu()
    {
        System.out.println("\n===== Emergency Ready =====");
        System.out.println("1. Add Emergency Patient");
        System.out.println("2. Add Regular Patient");
        System.out.println("3. Remove Patient");
        System.out.println("4. Call Next Patient");
        System.out.println("5. Display Waiting List");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
    }


    /**
     * Displays the current patient waiting list.
     *
     * @param deque
     *            the patient deque to display
     */
    public void displayWaitingList(PatientDeque deque)
    {
        System.out.println("\n===== Patient Waiting List =====");

        if (deque == null || deque.isEmpty())
        {
            System.out.println("The waiting list is empty.");
            return;
        }

        deque.displayWaitingList();
    }


    /**
     * Displays a general message.
     *
     * @param message
     *            the message to display
     */
    public void displayMessage(String message)
    {
        System.out.println(message);
    }


    /**
     * Displays an error message.
     *
     * @param error
     *            the error message to display
     */
    public void displayError(String error)
    {
        System.out.println("ERROR: " + error);
    }


    /**
     * Displays information about a patient.
     *
     * @param patient
     *            the patient to display
     */
    public void displayPatient(Patient patient)
    {
        if (patient == null)
        {
            displayError("No patient information available.");
            return;
        }

        System.out.println("\n===== Patient Information =====");
        System.out.println(patient.toString());
    }
}
