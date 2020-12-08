package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day08 extends Solution {
    @Getter
    private final int day = 8;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{2025, 2001};
    }

    @Override
    protected void run(List<String> input) {
        a = runStartCode(input).acc;
        for (int i = 0; i < input.size(); i++) {
            List<String> instructionsCopy = new ArrayList<>(input);
            String newVal = input.get(i).replaceAll("nop", "jmd");
            newVal = newVal.replaceAll("jmp", "nop");
            newVal = newVal.replaceAll("jmd", "jmp");

            instructionsCopy.set(i, newVal);

            Result res = runStartCode(instructionsCopy);

            if(!res.infinite) {
                b = res.acc;
                break;
            }
        }
    }

    public Result runStartCode(List<String> input) {
        int index = 0;
        List<Integer> passedIndexes = new ArrayList<>();
        boolean alreadyPassed = false;
        int acc = 0;

        while (true) {
            if(index == input.size()) {
                break;
            }
            if(passedIndexes.contains(index)) {
                alreadyPassed = true;
                break;
            }

            passedIndexes.add(index);
            String instruction = input.get(index);
            String[] parts = instruction.split(" ");
            parts[1] = parts[1].replaceAll("\\+", "");

            switch (parts[0]) {
                case "nop": {
                    index++;
                    break;
                }
                case "acc": {
                    index++;
                    acc += Integer.parseInt(parts[1]);
                    break;
                }
                case "jmp": {
                    index += Integer.parseInt(parts[1]);
                    break;
                }
                default: {
                    System.out.println(parts[0] + " instruction was not found");
                }
            }
        }

        return new Result(alreadyPassed, acc);
    }

    public class Result {
        boolean infinite;
        int acc;

        public Result(boolean infinite, int acc) {
            this.infinite = infinite;
            this.acc = acc;
        }
    }
}