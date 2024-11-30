//seat.java
package util;
public class Seat {
    private int seatId;
    private boolean reserved;

    public Seat(int seatId, boolean reserved) {
        this.seatId = seatId;
        this.reserved = reserved;
    }

    public int getSeatId() {
        return seatId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
