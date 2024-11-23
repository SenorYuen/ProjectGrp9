package Implementation;

import java.util.ArrayList;

interface Person {
    Receipt makePayment(double amount);
    ArrayList<Movie> browseCatalog(Theater theater);
    Ticket pickSeat();
}
