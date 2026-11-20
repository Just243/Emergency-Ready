
import student.TestCase;

/**
 * Tests the methods in the LoginManager class.
 *
 * @author Noah Rakvica
 * @version 2026-11-20
 */
public class LoginManagerTest extends TestCase {

    private LoginManager manager;

    /**
     * Sets up the test cases.
     */
    public void setUp() {
        manager = new LoginManager();
    }

    /**
     * Tests the constructor.
     */
    public void testConstructor() {
        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging in with correct credentials.
     */
    public void testSuccessfulLogin() {
        assertTrue(manager.login("admin", "1234"));
        assertTrue(manager.isLoggedIn());
    }

    /**
     * Tests logging in with an incorrect username.
     */
    public void testIncorrectUsername() {
        assertFalse(manager.login("wrong", "1234"));
        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging in with an incorrect password.
     */
    public void testIncorrectPassword() {
        assertFalse(manager.login("admin", "wrong"));
        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging in with null credentials.
     */
    public void testNullCredentials() {
        assertFalse(manager.login(null, "1234"));
        assertFalse(manager.login("admin", null));
        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging in with empty credentials.
     */
    public void testEmptyCredentials() {
        assertFalse(manager.login("", ""));
        assertFalse(manager.login("   ", "1234"));
        assertFalse(manager.login("admin", "   "));
        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging out after a successful login.
     */
    public void testLogout() {
        assertTrue(manager.login("admin", "1234"));
        assertTrue(manager.isLoggedIn());

        manager.logout();

        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging out when already logged out.
     */
    public void testLogoutWhenAlreadyLoggedOut() {
        manager.logout();

        assertFalse(manager.isLoggedIn());
    }

    /**
     * Tests logging in again after logging out.
     */
    public void testLoginAfterLogout() {
        assertTrue(manager.login("admin", "1234"));

        manager.logout();

        assertFalse(manager.isLoggedIn());
        assertTrue(manager.login("admin", "1234"));
        assertTrue(manager.isLoggedIn());
    }
}
