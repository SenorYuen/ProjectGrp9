package util;

public class LoginSession {
    private boolean isAuthenticated;
    private int personId;
    private String userType;
    private String status;
    private String cardNumber;  // Add cardNumber field


    public LoginSession() {
        this.isAuthenticated = false;
    }

    // Getters and Setters
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        // Assuming card number is stored in a variable `cardNumber` in LoginSession
        return this.cardNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}