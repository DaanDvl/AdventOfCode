package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<Integer> nums = inputListToIntegers(input);

        for (int x : nums) {
            for (int y : nums) {
                if(a == 0 && x+y == 2020) {
                    a = x * y;
                }
                for (int z : nums) {
                    if(b == 0 && x+y+z == 2020) {
                        b = x * y * z;
                    }
                }
            }
        }
    }

    private List<Integer> inputListToIntegers(List<String> input) {
        List<Integer> nums = new LinkedList<>();
        for(String n : input) {
            nums.add(Integer.parseInt(n));
        }
        return nums;
    }
}