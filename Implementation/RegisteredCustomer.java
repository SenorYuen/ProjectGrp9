/*
* File Name: RegisteredCustomer.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

package Implementation;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisteredCustomer implements Person{
    private String name;
    private String email;
    private String password;
    private String cardNumber;
    private LocalDate accountCreation;

    // Constructor
    public RegisteredCustomer(String name, String email, String password, String cardNumber, LocalDate optional) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        if (optional != null) {
            this.accountCreation = optional;
        }
        else {
            accountCreation = LocalDate.now();
        }
    }

    // GETTERS and SETTERS
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCardNumber() {
        return this.cardNumber;
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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getLocalDate() {
        return accountCreation;
    }

    // End of GETTERS and SETTERS

    @Override
    public Receipt makePayment(String theaterLocation, String movieChosen, int seatNumber, double amountPaid){
        /* Purpose of this function is to allow the user to purchase a ticket
         * REQURIES: The THEATER the MOVIE is playing along with the SEAT the user chose and the AMOUNT_PAID
         * RETURNS: a Receipt confirming that the user bought it; Must also change seat status
         */

        Ticket ticket = new Ticket(String.valueOf(seatNumber), movieChosen, theaterLocation);

        Payment paymentMade = new Payment(
            LocalDate.now(),
            amountPaid,
            "Credit Card",
            ticket
        );

        // This function should probably call the sendReceipt() function too but thats a later issue
        return paymentMade.getReceipt();
    }

    @Override
    public ArrayList<Movie> browseCatalog(Theater theater){
        // This function is just for retriving the theater's movie library
        return theater.getCatalog();
    }


}
