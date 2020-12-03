package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.List;

public class Day03 extends Solution {
    @Getter
    private final int day = 3;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{145, 3424528800L};
    }

    public Slope[] slopes = new Slope[]{
            new Slope(3,1),
            new Slope(1,1),
            new Slope(5,1),
            new Slope(7,1),
            new Slope(1,2)
    };


    @Override
    protected void run(List<String> input) {
        b = 1;
        for(Slope slope : slopes) {
            int trees = slope.getTrees(input);
            if(slope == slopes[0]) a = trees;
            b *= trees;
        }
    }

    public char getCharAtCoordinate(List<String> field, int x, int y) {
        if(y >= field.size()) return 'e';
        String row = field.get(y);
        int index = x % row.length();
        return row.charAt(index);
    }

    private class Slope {
        @Getter
        private final int right;
        @Getter
        private final int down;

        public Slope(int right, int down) {
            this.right = right;
            this.down = down;
        }

        public int getTrees(List<String> input) {
            int currentX = 0;
            int currentY = 0;
            int trees = 0;
            while (true) {
                currentX += getRight();
                currentY += getDown();
                char coordChar = getCharAtCoordinate(input, currentX, currentY);

                if (coordChar == '#') trees++;
                if (coordChar == 'e') break;
            }

            return trees;
        }
    }
}