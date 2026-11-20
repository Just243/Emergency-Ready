
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Handles user accounts, login, logout, and password changes.
 *
 * @author Noah
 * @version 2026-11-20
 */
public class LoginManager
{
    private Map<String, String> accounts;
    private String currentUsername;
    private static final String FILE_NAME = "accounts.txt";

    /**
     * Constructs the LoginManager and loads saved accounts.
     */
    public LoginManager()
    {
        accounts = new HashMap<String, String>();
        currentUsername = null;

        loadAccounts();

        if (accounts.isEmpty())
        {
            accounts.put("admin", "1234");
            saveAccounts();
        }
    }

    /**
     * Loads accounts from the saved file.
     */
    private void loadAccounts()
    {
        File file = new File(FILE_NAME);

        if (!file.exists())
        {
            return;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("=", 2);

                if (parts.length == 2
                    && !parts[0].trim().isEmpty()
                    && !parts[1].trim().isEmpty())
                {
                    accounts.put(parts[0], parts[1]);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not load saved accounts.");
        }
    }

    /**
     * Saves all accounts to the file.
     *
     * @return true if saving was successful
     */
    private boolean saveAccounts()
    {
        try (PrintWriter writer =
                new PrintWriter(new FileWriter(FILE_NAME)))
        {
            for (String username : accounts.keySet())
            {
                writer.println(username + "=" + accounts.get(username));
            }

            return true;
        }
        catch (IOException e)
        {
            System.out.println("Could not save accounts.");
            return false;
        }
    }

    /**
     * Creates a new account.
     *
     * @param username the new username
     * @param password the new password
     * @return true if the account was created
     */
    public boolean createAccount(String username, String password)
    {
        if (username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty())
        {
            return false;
        }

        username = username.trim();

        if (username.contains("=") || accounts.containsKey(username))
        {
            return false;
        }

        accounts.put(username, password);

        if (!saveAccounts())
        {
            accounts.remove(username);
            return false;
        }

        return true;
    }

    /**
     * Logs in a user.
     *
     * @param username the username
     * @param password the password
     * @return true if login succeeds
     */
    public boolean login(String username, String password)
    {
        if (username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty())
        {
            return false;
        }

        username = username.trim();

        if (accounts.containsKey(username)
            && accounts.get(username).equals(password))
        {
            currentUsername = username;
            return true;
        }

        return false;
    }

    /**
     * Changes the current user's password.
     *
     * @param oldPassword the current password
     * @param newPassword the new password
     * @return true if the password was changed
     */
    public boolean changePassword(
        String oldPassword, String newPassword)
    {
        if (!isLoggedIn() || oldPassword == null
            || newPassword == null || newPassword.trim().isEmpty())
        {
            return false;
        }

        if (!accounts.get(currentUsername).equals(oldPassword))
        {
            return false;
        }

        String previousPassword = accounts.get(currentUsername);
        accounts.put(currentUsername, newPassword);

        if (!saveAccounts())
        {
            accounts.put(currentUsername, previousPassword);
            return false;
        }

        return true;
    }

    /**
     * Logs out the current user.
     */
    public void logout()
    {
        currentUsername = null;
    }

    /**
     * Checks whether someone is logged in.
     *
     * @return true if a user is logged in
     */
    public boolean isLoggedIn()
    {
        return currentUsername != null;
    }
}
