/*
* File Name: RegisteredCustomer.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

package Implementation;

public class RegisteredCustomer {
    private String name;
    private String email;
    private String password;
    private String address;
    private int cardNumber;
    private int accountNumber;
    private int dateCreated;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAddress() {
        return this.address;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public int getDateCreated() {
        return this.dateCreated;
    }

    public void setName(String desiredName) {
        this.name = desiredName;
    }

    public void setEmail(String desiredEmail) {
        this.email = desiredEmail;
    }

    public void setPassword(String desiredPassword) {
        this.password = desiredPassword;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

}
