package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Solution {
    @Getter
    private final int day = 6;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{};
    }

    @Override
    protected void run(List<String> input) {
        String inputString = String.join("\n", input);
        String[] groups = inputString.split("\n\n");

        for (String group : groups) {
            group = group.replaceAll("\n", " ");
            Set<Character> charSet = new HashSet<>();

            String[] answers = group.split(" ");

            for (String a : answers) {
                for (char c : a.toCharArray()) {
                    boolean inAll = true;
                    for (String o : answers) {
                        if (!o.contains(c + "")) {
                            inAll = false;
                            break;
                        }
                    }

                    if (inAll && !charSet.contains(c)) {
                        b++;
                    }

                    charSet.add(c);
                }
            }

            a += charSet.size();
        }
    }
}