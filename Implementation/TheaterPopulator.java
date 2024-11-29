package Implementation;

import java.util.ArrayList;

public class TheaterPopulator {
    
    public static Theater populateTheater(){
        ArrayList<Seat> seatMap = new ArrayList<Seat>();
        for(int i = 0; i < 20; i++){
            Seat seatCreated = new Seat(i, false, 90.00);
            seatMap.add(seatCreated);
        }

        Movie movie1 = new Movie(
            "Finding Fahmi", 
            30,
            new Date(3, "September", 2004, "13:00:03"),
            seatMap
        );
        
        Movie movie2 = new Movie(
            "Stacklin's enqueue to the Heap",
            900,
            new Date(5, "April", 1960, "00:00:00"),
            seatMap
        );

        Movie movie3 = new Movie(
            "Odin's Downfall",
            1000,
            new Date(8,"May",1800, "10:03:59"),
            seatMap
        );

        ArrayList<Movie> catalog = new ArrayList<>();
        catalog.add(movie1);
        catalog.add(movie2);

        ArrayList<Movie> unreleasedCatalog = new ArrayList<>();
        unreleasedCatalog.add(movie3);


        Theater theater = new Theater(
            catalog,
            unreleasedCatalog,
            "Russia"
        );

        return theater;
    }

}
