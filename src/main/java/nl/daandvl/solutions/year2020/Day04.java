package nl.daandvl.solutions.year2020;

import lombok.Getter;
import nl.underkoen.adventofcode.solutions.Solution;
import nl.underkoen.adventofcode.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day04 extends Solution {
    @Getter
    private final int day = 4;
    @Getter
    private final int year = 2020;

    @Override
    public long[] getCorrectOutput() {
        return new long[]{242, 186};
    }

    public String[] requiredParts = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    @Override
    protected void run(List<String> input) {
        String inputString = String.join("\n", input);
        String[] passports = inputString.split("\n\n");

        for (String passport : passports) {
            passport = passport.replace("\n", " ");
            String[] parts = passport.split(" ");
            boolean valid = true;

            for (String required : requiredParts) {
                if (!passport.contains(required + ":")) {
                    valid = false;
                    break;
                }
            }

            if(!valid) continue;

            a++;

            for (String part : parts) {
                String[] partInfo = part.split(":");
                String type = partInfo[0];
                String value = partInfo[1];
                switch (type) {
                    case "byr": {
                        int yr = Integer.parseInt(value);
                        if (yr < 1920 || yr > 2002) valid = false;
                        break;
                    }
                    case "iyr": {
                        int yr = Integer.parseInt(value);
                        if (yr < 2010 || yr > 2020) valid = false;
                        break;
                    }
                    case "eyr": {
                        int yr = Integer.parseInt(value);
                        if (yr < 2020 || yr > 2030) valid = false;
                        break;
                    }
                    case "hgt": {
                        if (value.endsWith("cm")) {
                            int height = Integer.parseInt(value.substring(0, value.length()-2));
                            if (height < 150 || height > 193) {
                                valid = false;
                            }
                        } else if (value.endsWith("in")) {
                            int height = Integer.parseInt(value.substring(0, value.length()-2));
                            if (height < 59 || height > 76) {
                                valid = false;
                            }
                        } else {
                            valid = false;
                        }
                        break;
                    }
                    case "hcl": {
                        Pattern pattern = Pattern.compile("^#([a-fA-F0-9]{6})$");
                        if (!pattern.matcher(value).matches()) valid = false;
                        break;
                    }
                    case "ecl": {
                        String[] colors = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                        if (!Arrays.asList(colors).contains(value)) valid = false;
                        break;
                    }
                    case "pid": {
                        Pattern pattern = Pattern.compile("^\\d{9}$");
                        if (!pattern.matcher(value).matches()) valid = false;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            if (valid) b++;
        }


    }
}