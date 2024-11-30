package util;

public class LoginSession {
    private String username;
    private String password;
    private boolean isAuthenticated;

    public LoginSession() {
        this.isAuthenticated = false;
    }

    public void login(String username, String password) {
        // Database logic to validate credentials
        if (authenticate(username, password)) {
            this.username = username;
            this.password = password;
            this.isAuthenticated = true;
        }
    }

    public boolean authenticate(String username, String password) {
        // Implement your database query to verify user credentials.
        // Example (you can use prepared statements here for security):
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        // Execute the query and return true if the credentials match
        return true; // Replace this with actual query logic
    }

    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public String getUsername() {
        return username;
    }

    public void logout() {
        this.username = null;
        this.password = null;
        this.isAuthenticated = false;
    }
}
