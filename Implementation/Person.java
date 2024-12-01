package Implementation;

import java.util.ArrayList;

interface Person {
    Receipt makePayment(String theaterLocation, String movieChosen, int seatNumber, double amountPaid);
    ArrayList<Movie> browseCatalog(Theater theater);

    public void setEmail(String desiredEmail);

    public void setPassword(String desiredPassword);

}
