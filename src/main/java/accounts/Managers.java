package accounts;

/**
 * Class for holding Manager information
 */
public class Managers {
    private String username;
    private String password;

    public Managers() {
        this.username = "";
        this.password = "";
    }

    public Managers(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username:" + username + "\nPassword:" + password;
    }
}
