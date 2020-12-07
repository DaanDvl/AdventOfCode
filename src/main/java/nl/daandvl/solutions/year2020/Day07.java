package nl.daandvl.solutions.year2020;

import lombok.Getter;
import lombok.ToString;
import nl.underkoen.adventofcode.solutions.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 extends Solution {
    @Getter
    private final int day = 7;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{};
    }

//    @Override
//    public String[] getCorrectOutputText() {
//        return new String[]{};
//    }

    @Override
    protected void run(List<String> input) {
        for(String rule : input) {
            new Bag(rule);
        }

        Bag.bags.forEach(Bag::calculateContents);

        a = Bag.bags.stream().filter(bag -> bag.containsBag(Bag.getBagByColor("shiny gold"))).count();
        b = Bag.getBagByColor("shiny gold").amountOfBags();
    }

    @ToString(of = {"color"})
    private static class Bag {

        private String color;
        private String contentLine;

        private Map<Bag, Integer> contents;

        private static List<Bag> bags = new ArrayList<>();

        public Bag(String bagLine) {
            String[] parts = bagLine.split(" bags contain ");
            this.color = parts[0];
            this.contentLine = parts[1];
            bags.add(this);
        }

        public void calculateContents() {
            this.contents = new HashMap<>();

            String[] parts = contentLine.replaceAll("\\.", "")
                    .replaceAll(" bags", "")
                    .replaceAll(" bag", "")
                    .split(", ");

            for (String part : parts) {
                if (part.equals("no other")) break;

                String[] details = part.split(" ", 2);
                int amount = Integer.parseInt(details[0]);
                Bag bag = getBagByColor(details[1]);
                contents.put(bag, amount);
            }

        }

        public boolean containsBag(Bag inputBag) {
            boolean result = false;
            for (Bag bag : contents.keySet()) {
                if (bag == inputBag) {
                    result = true;
                    break;
                }
                if (bag.containsBag(inputBag)) result = true;
            }

            return result;
        }

        public int amountOfBags() {
            int result = 0;
            for (Bag bag : contents.keySet()) {
                result += contents.get(bag);
                result += contents.get(bag) * bag.amountOfBags();
            }

            return result;
        }

        public static Bag getBagByColor(String color) {
            return bags.stream()
                    .filter(b -> b.color.equals(color))
                    .findAny()
                    .orElse(null);
        }
    }
}