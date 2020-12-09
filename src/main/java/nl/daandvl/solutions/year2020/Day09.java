package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.List;
import java.util.stream.LongStream;

public class Day09 extends Solution {
    @Getter
    private final int day = 9;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{};
    }

    private final int preamble = 25;

    protected void run(List<String> input) {
        for (int i = preamble; i < input.size(); i++) {
            long value = Long.parseLong(input.get(i));
            List<String> front = input.subList(i - preamble, i);

            boolean complies = false;
            for (String inFront : front) {
                long num = Long.parseLong(inFront);
                if (front.contains(value - num + "")) {
                    complies = true;
                    break;
                }
            }

            if (!complies) {
                a = value;
                break;
            }
        }

        for (int i = 0; i < input.size(); i++) {
            for(int end = i+2; end < input.size(); end++) {
                List<String> range = input.subList(i, end);
                long sum = range.stream().mapToLong(Long::parseLong).sum();
                if (sum > a) break;
                if (sum == a) {
                    long max = range.stream().mapToLong(Long::parseLong).max().getAsLong();
                    long min = range.stream().mapToLong(Long::parseLong).min().getAsLong();

                    b = max + min;
                    break;
                }
            }
            if(b != 0) break;
        }
    }
}