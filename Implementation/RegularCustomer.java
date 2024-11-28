/*
* File Name: RegularCustomer.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

package Implementation;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegularCustomer implements Person{
    private String email;

    // Constructor
    public RegularCustomer(String email){
        this.email = email;
    }

    // GETTERS and SETTERS
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
