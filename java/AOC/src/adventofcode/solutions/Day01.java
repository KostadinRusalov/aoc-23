package adventofcode.solutions;

import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Day01 {
    private static final Map<String, Integer> nums = Map.ofEntries(
        entry("one", 1), entry("1", 1),
        entry("two", 2), entry("2", 2),
        entry("three", 3), entry("3", 3),
        entry("four", 4), entry("4", 4),
        entry("five", 5), entry("5", 5),
        entry("six", 6), entry("6", 6),
        entry("seven", 7), entry("7", 7),
        entry("eight", 8), entry("8", 8),
        entry("nine", 9), entry("9", 9)
    );

    public static int calibrate1(String input) {
        Pattern pattern = Pattern.compile("\\d.*\\d|\\d");

        return Pattern.compile("\\n")
            .splitAsStream(input)
            .mapToInt(str -> {
                var m = pattern.matcher(str);
                m.find();
                return (str.charAt(m.start()) - '0') * 10 + (str.charAt(m.end() - 1) - '0');
            }).sum();
    }

    public static int calibrate2(String input) {
        Pattern pattern = Pattern.compile(
            "(one|two|three|four|five|six|seven|eight|nine|\\d).*" +
                "(one|two|three|four|five|six|seven|eight|nine|\\d)" +
                "|(one|two|three|four|five|six|seven|eight|nine|\\d)");

        return Pattern.compile("\\n")
            .splitAsStream(input)
            .mapToInt(str -> {
                var m = pattern.matcher(str);
                m.find();
                return m.group(3) == null ? nums.get(m.group(1)) * 10 + nums.get(m.group(2)) :
                    nums.get(m.group(3)) * 11;
            }).sum();
    }
}
