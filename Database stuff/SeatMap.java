//seatmap.java
package util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatMap {

    public static void displaySeatMap() {
        // Create a list of 20 seats
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            seats.add(new Seat(i, false)); // Seat ID, Available (false)
        }

        System.out.println("\n=== Seat Map ===");
        for (int i = 0; i < 20; i++) {
            System.out.print("Seat " + i + (seats.get(i).isReserved() ? " (Reserved)" : " (Available)") + " | ");
            if ((i + 1) % 5 == 0) {
                System.out.println(); // New line every 5 seats
            }
        }
    }

    public static void reserveSeat(List<Seat> seats, int seatNumber) {
        if (seats.get(seatNumber).isReserved()) {
            System.out.println("This seat is already reserved!");
        } else {
            seats.get(seatNumber).setReserved(true);
            System.out.println("Seat " + seatNumber + " reserved successfully!");
        }
    }
}
