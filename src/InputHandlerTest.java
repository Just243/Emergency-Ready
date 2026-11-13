import student.TestCase;

import java.io.ByteArrayInputStream;
import java.util.Scanner;


/**
 * Tests the InputHandler class.
 * Verifies that user input is correctly read and validated.
 *
 * @author Group 53
 * @version 2026-11-12
 */
public class InputHandlerTest extends TestCase{

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
     * Tests valid integer input.
     */
    public void testGetIntInput() {
        setInput("5\n");

        assertEquals(5, InputHandler.getIntInput());
    }


    /**
     * Tests invalid integer input followed by valid input.
     */
    public void testGetIntInputInvalid() {
        setInput("abc\n3\n");

        assertEquals(3, InputHandler.getIntInput());
    }


    /**
     * Tests valid string input.
     */
    public void testGetStringInput() {
        setInput("John\n");

        assertEquals("John", InputHandler.getStringInput());
    }


    /**
     * Tests empty string input followed by valid input.
     */
    public void testGetStringInputEmpty() {
        setInput("\nJohn\n");

        assertEquals("John", InputHandler.getStringInput());
    }


    /**
     * Tests boolean input representing true.
     */
    public void testGetBooleanInputTrue() {
        setInput("yes\n");

        assertTrue(InputHandler.getBooleanInput());
    }


    /**
     * Tests boolean input representing false.
     */
    public void testGetBooleanInputFalse() {
        setInput("no\n");

        assertFalse(InputHandler.getBooleanInput());
    }


    /**
     * Tests invalid boolean input followed by valid input.
     */
    public void testGetBooleanInputInvalid() {
        setInput("maybe\nyes\n");

        assertTrue(InputHandler.getBooleanInput());
    }
}
