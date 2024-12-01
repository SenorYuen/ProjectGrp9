package Implementation;

import java.util.ArrayList;

public class TheaterPopulator {

    public static Theater populateTheater(){
        ArrayList<Seat> seatMap = new ArrayList<Seat>();
        for(int i = 1; i < 21; i++){
            Seat seatCreated = new Seat(i, false, 90.00);
            seatMap.add(seatCreated);
        }

        Movie movie1 = new Movie(
            "Finding Fahmi",
            30,
            new Date(3, 9, 2004, "13:00"),
            seatMap
        );

        Movie movie2 = new Movie(
            "Stacklin's enqueue to the Heap",
            900,
            new Date(5, 4, 1960, "00:00"),
            seatMap
        );

        Movie movie3 = new Movie(
            "Odin's Downfall",
            1000,
            new Date(8,5,2800, "10:03"),
            seatMap
        );

        ArrayList<Movie> catalog = new ArrayList<>();
        catalog.add(movie1);
        catalog.add(movie2);

        ArrayList<Movie> unreleasedCatalog = new ArrayList<>();
        unreleasedCatalog.add(movie3);

        RegisteredCustomer user1 = new RegisteredCustomer(
        "phil", "phil", "phil", "1234"
        );

        RegisteredCustomer user2 = new RegisteredCustomer(
        "dada", "dada", "dada", "5678"
        );

        ArrayList<RegisteredCustomer> userStorage = new ArrayList<>();
        userStorage.add(user1);
        userStorage.add(user2);

        Theater theater = new Theater(
            catalog,
            unreleasedCatalog,
            "Russia",
            userStorage
        );

        return theater;
    }

}
