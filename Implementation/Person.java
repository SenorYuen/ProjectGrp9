package Implementation;

import java.util.ArrayList;

interface Person {
    Receipt makePayment(Theater theater, Movie movieChosen, Seat seatChosen, double amountPaid);
    ArrayList<Movie> browseCatalog(Theater theater);
}
