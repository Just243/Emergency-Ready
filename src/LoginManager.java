
/**
 * Handles user authentication and login status. This class is part of the
 * model.
 */
public class LoginManager
{

    private String username;
    private String password;

    /**
     * Constructs a LoginManager.
     */
    public LoginManager()
    {
        username = null;
        password = null;
    }


    /**
     * Validates user credentials.
     *
     * @param username
     *            the username entered
     * @param password
     *            the password entered
     * @return true if login succeeds, false otherwise
     */
    public boolean login(String username, String password)
    {

        if (username == null || username.trim().isEmpty() || password == null
            || password.trim().isEmpty())
        {
            return false;
        }

        if (username.equals("admin") && password.equals("1234"))
        {
            this.username = username;
            this.password = password;
            return true;
        }

        return false;
    }


    /**
     * Logs out the current user.
     */
    public void logout()
    {
        username = null;
        password = null;
    }


    /**
     * Checks whether a user is currently logged in.
     *
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn()
    {
        return username != null && password != null;
    }
}
