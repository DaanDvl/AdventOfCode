package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends Solution {
    @Getter
    private final int day = 5;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{919, 642};
    }

    @Override
    protected void run(List<String> input) {
        for (String seatString : input) {
            int seatId = new Seat(seatString).getSeatID();
            if (seatId > a) a = seatId;
        }

        /* Ugly bruteforce this will take ages */
        for (int row = 0; row < 127; row++) {
            for (int col = 0; col < 8; col++) {
                int available = Seat.isAvailable(row, col);

                if (available == -1) break;

                if (available == 1) {
                    b = new Seat(row, col).getSeatID();
                    break;
                }
            }
        }
    }

    private static class Seat {
        private String seatString;
        private final int row;
        private final int column;
        private int id = -1;

        private static List<Seat> seats = new ArrayList<>();

        public Seat(String seatString) {
            this.seatString = seatString;
            this.row = calculateRow();
            this.column = calculateColumn();
            seats.add(this);
        }

        public Seat(int row, int col) {
            this.row = row;
            this.column = col;
            seats.add(this);
        }

        public int getSeatID() {
            if (this.id == -1) id = row * 8 + column;
            return id;
        }

        public int calculateRow() {
            int minRow = 0;
            int maxRow = 127;
            for (char c : seatString.substring(0, 7).toCharArray()) {
                if (c == 'B') {
                    minRow += (maxRow - minRow) / 2 + 1;

                } else if (c == 'F') {
                    maxRow -= (maxRow - minRow) / 2 + 1;
                }
            }
            return minRow;
        }

        public int calculateColumn() {
            int minCol = 0;
            int maxCol = 7;
            for (char c : seatString.substring(7, 10).toCharArray()) {
                if (c == 'R') {
                    minCol += (maxCol - minCol) / 2 + 1;
                } else if (c == 'L') {
                    maxCol -= (maxCol - minCol) / 2 + 1;
                }
            }
            return minCol;
        }

        public static int isAvailable(int row, int col) {
            Seat seat = getSeatByRowAndCol(row, col);

            if (seat != null) {
                return 0;
            }

            int seatId = row * 8 + col;

            if (getSeatById(seatId + 1) == null || getSeatById(seatId - 1) == null) {
                return -1;
            }

            return 1;
        }

        public static Seat getSeatByRowAndCol(int row, int col) {
            return seats.stream()
                    .filter(seat -> seat.row == row && seat.column == col)
                    .findAny().orElse(null);
        }

        public static Seat getSeatById(int id) {
            return seats.stream()
                    .filter(seat -> seat.getSeatID() == id)
                    .findAny().orElse(null);
        }
    }
}