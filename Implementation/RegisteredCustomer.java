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
    private Ticket ticket;
    private int cardNumber;
    private int accountNumber;
    private int dateCreated;

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

    public Ticket getTicket(){
        return this.ticket;
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

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
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
