
/**
 * Controller class for the Emergency Ready application.
 * Coordinates the patient waiting list, login system,
 * input handling, and user interface.
 *
 * @author Madison Le (group 53)
 * @version 2026-11-10
 */
public class EmergencyReady {

    private PatientDeque waitingList;
    private EmergencyReadyView view;
    private LoginManager loginManager;
    private boolean running;

    /**
     * Constructs the Emergency Ready application.
     */
    public EmergencyReady() {
        waitingList = new PatientDeque();
        view = new EmergencyReadyView();
        loginManager = new LoginManager();
        running = true;
    }


    /**
     * Runs the main application loop.
     */
    public void run() {
        // Login loop
        while (!loginManager.isLoggedIn()) {
            view.displayLoginMenu();

            String username = InputHandler.getStringInput();
            String password = InputHandler.getStringInput();

            if (!loginManager.login(username, password)) {
                view.displayError("Invalid username or password.");
            }
        }

        // Main application loop
        while (running && loginManager.isLoggedIn()) {
            view.displayMainMenu();

            int choice = InputHandler.getIntInput();

            switch (choice) {
                case 1:
                    addEmergencyPatient();
                    break;

                case 2:
                    addRegularPatient();
                    break;

                case 3:
                    removePatient();
                    break;

                case 4:
                    callNextPatient();
                    break;

                case 5:
                    displayWaitingList();
                    break;

                case 6:
                    loginManager.logout();
                    running = false;
                    view.displayMessage("Logged out successfully.");
                    break;

                default:
                    view.displayError("Invalid menu option.");
                    break;
            }
        }
    }


    /**
     * Adds an emergency patient to the front of the waiting list.
     */
    public void addEmergencyPatient() {
        view.displayMessage("Enter patient ID:");
        String patientID = InputHandler.getStringInput();

        view.displayMessage("Enter patient name:");
        String name = InputHandler.getStringInput();

        Patient patient = new Patient(patientID, name, true);

        waitingList.addFirst(patient);

        view.displayMessage("Emergency patient added successfully.");
    }


    /**
     * Adds a regular patient to the back of the waiting list.
     */
    public void addRegularPatient() {
        view.displayMessage("Enter patient ID:");
        String patientID = InputHandler.getStringInput();

        view.displayMessage("Enter patient name:");
        String name = InputHandler.getStringInput();

        Patient patient = new Patient(patientID, name, false);

        waitingList.addLast(patient);

        view.displayMessage("Regular patient added successfully.");
    }


    /**
     * Removes a patient from the waiting list using their ID.
     */
    public void removePatient() {
        if (waitingList.isEmpty()) {
            view.displayError("The waiting list is empty.");
            return;
        }

        view.displayMessage("Enter the patient ID to remove:");
        String patientID = InputHandler.getStringInput();

        boolean removed = waitingList.removePatient(patientID);

        if (removed) {
            view.displayMessage("Patient removed successfully.");
        }
        else {
            view.displayError("No patient found with that ID.");
        }
    }


    /**
     * Calls and removes the next patient from the waiting list.
     */
    public void callNextPatient() {
        if (waitingList.isEmpty()) {
            view.displayError("The waiting list is empty.");
            return;
        }

        Patient patient = waitingList.removeFirst();

        view.displayMessage("Calling next patient:");
        view.displayPatient(patient);
    }


    /**
     * Displays the current patient waiting list.
     */
    public void displayWaitingList() {
        view.displayWaitingList(waitingList);
    }
}
