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
Aaron Jacobson: Created the github, helped in several different classes

Madison Le: worked on the main classes responsible for running the program

Romeo Duarte: completed patient and patient deque

Noah Rakvica: Worked on patient and patient deque
# What's Next?

