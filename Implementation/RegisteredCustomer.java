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
    private String address;
    private int cardNumber;

    // Constructor
    public RegisteredCustomer(String name, String email, String password, String address, int cardNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cardNumber = cardNumber;
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

    public String getAddress() {
        return this.address;
    }

    public int getCardNumber() {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    // End of GETTERS and SETTERS

    @Override
    public Receipt makePayment(Theater theater, Movie movieChosen, Seat seatChosen, double amountPaid){
        /* Purpose of this function is to allow the user to purchase a ticket
         * REQURIES: The THEATER the MOVIE is playing along with the SEAT the user chose and the AMOUNT_PAID
         * RETURNS: a Receipt confirming that the user bought it; Must also change seat status
         */

        Ticket ticket = new Ticket(seatChosen, movieChosen, theater);

        Payment paymentMade = new Payment(
            LocalDate.now().toString(),
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
