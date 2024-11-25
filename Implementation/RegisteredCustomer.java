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
    public Receipt makePayment(double amountPaid){
        Ticket ticket = getTicket();

        Payment payment = new Payment(
            LocalDate.now().toString(), // payment date
            amountPaid,
            "Credit Card", //payment method
            ticket                        // ticket paid for
        );

        return payment.getReceipt();
    }

    @Override
    public ArrayList<Movie> browseCatalog(Theater theater){
        return theater.getCatalog();
    }

    @Override
    public Ticket pickSeat(Theater theater){
        // I figure this function should allow the customer to view the seat map and 
        // make a purchase of the seat
        ArrayList<Seat> seatMap = theater.getSeatMap();
        // probably need some kinda observer pattern i think to display this

        
    }


}
