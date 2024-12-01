package Implementation;

import java.time.LocalDate;

/*
* File Name: Payment.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

public class Payment {
    private LocalDate paymentDate;
    private String cardNumber;
    private Ticket ticket;
    private Receipt receipt;

    // Constructor
    public Payment(LocalDate paymentDate, double amountPaid, String cardNumber, Ticket ticket){
        this.paymentDate = paymentDate;
        this.cardNumber = cardNumber;
        this.ticket = ticket;
        this.receipt = new Receipt(
            amountPaid,
            paymentDate,
            ticket.getMovieName(),
            ticket.getSeatNumber()
        );
    }

    // GETTERS and SETTERS
    public Ticket getTicket(){
        return this.ticket;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public LocalDate getPaymentDate() {
        return this.paymentDate;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Receipt getReceipt(){
        return this.receipt;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // end of GETTERS and SETTERS

}
