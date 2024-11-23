package Implementation;

/*
* File Name: Payment.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

public class Payment {
    private String paymentDate;
    private String paymentMethod;
    private Ticket ticket;

    // Constructor
    public Payment(String paymentDate, String paymentMethod, Ticket ticket){
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.ticket = ticket;
    }

    // GETTERS and SETTERS
    public Ticket getTicket(){
        return this.ticket;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // end of GETTERS and SETTERS

}
