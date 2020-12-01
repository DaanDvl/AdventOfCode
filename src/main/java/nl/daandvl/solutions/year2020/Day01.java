package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;
import nl.underkoen.adventofcode.utils.InputUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day01 extends Solution {
    @Getter
    private final int day = 1;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{1019571, 100655544};
    }

    @Override
    protected void run(List<String> input) {
        Set<Long> nums = InputUtils.asNumberList(input)
                .collect(Collectors.toSet());

        for (long x : nums) {
            for (long y : nums) {
                if(a == 0 && x+y == 2020) {
                    a = x * y;
                }
                for (long z : nums) {
                    if(b == 0 && x+y+z == 2020) {
                        b = x * y * z;
                        break;
                    }
                }
                if(a != 0 && b != 0) break;
            }
            if(a != 0 && b != 0) break;
        }
    }
}