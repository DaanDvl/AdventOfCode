package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;
import nl.underkoen.adventofcode.utils.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 extends Solution {
    @Getter
    private final int day = 10;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{};
    }

    @Override
    protected void run(List<String> input) {
        input.add("0");
        List<Long> sortedInput = InputUtils.asNumberList(input).sorted().collect(Collectors.toList());

        Map<Long, List<Long>> possibilities = new HashMap<>();
        int oneDifference = 0, threeDifference = 1;
        long lastLong = 0;

        for (long number : sortedInput) {
            long difference = number-lastLong;
            if (difference == 1) {
                oneDifference++;
            } else if (difference == 3) {
                threeDifference++;
            }

            int currentIndex = sortedInput.indexOf(number);
            List<Long> pos = new ArrayList<>();
            for (int nextThree = currentIndex+1; nextThree < currentIndex+4; nextThree++) {
                if (nextThree >= sortedInput.size()) {
                    break;
                }
                long next = sortedInput.get(nextThree);
                if (next - number <= 3) {
                    pos.add(next);
                }
            }

            possibilities.put(number, pos);
            lastLong = number;
        }

        a = oneDifference*threeDifference;
        b = getPossibilities(0L, possibilities);
    }

    private final Map<Long, Long> cache = new HashMap<>();

    public long getPossibilities(long from, Map<Long, List<Long>> possibilities) {
        if (cache.containsKey(from)) return cache.get(from);
        long res = 0;

        for (long val : possibilities.get(from)) {
            long tmp = getPossibilities(val, possibilities);
            cache.put(val, tmp);
            res += tmp;
        }

        return res == 0 ? 1 : res;
    }
}