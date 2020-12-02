package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.List;

public class Day02 extends Solution {
    @Getter
    private final int day = 2;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{542, 360};
    }

    @Override
    protected void run(List<String> input) {
        for(String i : input) {
            String[] parts = i.split(" ");

            int min = Integer.parseInt(parts[0].split("-")[0]);
            int max = Integer.parseInt(parts[0].split("-")[1]);

            char toCount = parts[1].charAt(0);
            String password = parts[2];

            long counted = password.chars().filter(ch -> ch == toCount).count();
            boolean policyOne = counted >= min && counted <= max;
            if(policyOne) a++;

            if (password.charAt(min-1) == toCount && password.charAt(max-1) == toCount) {
                continue;
            }

            if (password.charAt(min-1) == toCount || password.charAt(max-1) == toCount) {
                b++;
            }
        }

    }
}