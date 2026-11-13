How to run: 
Import the project into an IDE that can run Java. 
Go to the src/Main.java class and run the class. 

How to run tests:
For Eclipse IDE, go into each individual test class and run them as a JUnit Test. 

# Reflection
#### What changed from our specification document?

Compared to our original specification, Emergency Ready has remained focused on managing a single patient waiting list, but some implementation details have changed as we developed the project. Our original design separated the program into model, view, and controller classes, with each class having a specific role to play in the program. We maintained this general structure while implementing the application.

One implementation detail that changed was how input handling was organized. Instead of having the controller independently validate every input, we created an InputHandler class to centralize common input operations. This allows methods such as integer, string, and boolean input to be reused throughout the application. The controller still handles application-specific validation, such as determining whether a menu selection is valid or whether a patient ID already exists.

Our test plan also became more solid  as we implemented the project. We began creating separate test classes for the application's components, including InputHandler and EmergencyReady, instead of relying exclusively on manually testing the application through the terminal. This allows us to test individual methods and identify problems without running the entire application.

# Work Distribution
Madison Le: Wrote main, EmergencyReady, EmergencyReadyTest, LoginManager, and EmergencyReadyView.
Romeo Duarte: Completed Patient and PatientDeque.
Aaron Jacobson: Created the GitHub repository and helped in several classes.
Noah Rakvica: Worked on Patient and PatientDeque.

# What's Next?

We have the functions necessary in order to meet our MVP.

We plan to add a user authentication system that would be able to store usernames and passwords and provide a way to change passwords. We also plan to add a database that stores patient info and their history. For most of this we would have to create new classes but we would have to change the main method in order for this to work. This is part of our stretch goals. Some things that we might add but aren't sure yet would be the implementation of specialized operating rooms (x-ray, cardio, neuro, etc) with a way to match with specific doctors who specialize in those areas.

we will need to provide tests for user authentication, making sure that a username/password combination that doesn't exist in the database does not grant access into the database. We will need to make tests for making a patient report and patient history, making sure invalid input for patient info and the procedures each patient underwent is properly accounted for.

### Our Change Feature

We plan to add a user authentication system. This would allow the program to store usernames and passwords and give users a way to change their passwords. We expect this feature to affect the LoginManager and Main classes, and may require a new class to store user information. The Patient and PatientDeque classes should not need to change.

### Which stretch goals will we attempt?

We plan to attempt user authentication and a database that stores patient information and patient history. Other possible stretch goals include specialized operating rooms, such as X-ray, cardio, and neuro, and matching patients with doctors who specialize in those areas. We have not decided whether we will implement these additional features.

### What new tests will we need?

We will need tests for creating accounts, logging in with correct and incorrect information, and changing passwords. If we add a patient database, we will also need to test saving, retrieving, and updating patient information.

#### Who does what?

Aaron: Help with connecting classes and testing the overall program.

Madison: Continue working on the main program and view.

Romeo: Help with patient management and any additional features.

Noah: Continue working on testing and help implement the planned features.
