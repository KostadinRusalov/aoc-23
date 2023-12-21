package adventofcode.solutions;

import java.util.regex.Pattern;

public class Day01 {
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

    public static int calibrate2(String text) {
        Pattern pattern = Pattern.compile(
            "(one|two|three|four|five|six|seven|eight|nine|\\d).*" +
                "(one|two|three|four|five|six|seven|eight|nine|\\d)" +
                "|one|two|three|four|five|six|seven|eight|nine|\\d");

        return Pattern.compile("\\n")
            .splitAsStream(text)
            .mapToInt(word -> {
                var m = pattern.matcher(word);
                m.find();
                return convertS(word, m.start()) * 10 + convertE(word, m.end() - 1);
            }).sum();
    }

    public static int convertS(String word, int start) {
        if (Character.isDigit(word.charAt(start))) {
            return word.charAt(start) - '0';
        }
        return switch (word.substring(start, start + 2)) {
            case "on" -> 1;
            case "tw" -> 2;
            case "th" -> 3;
            case "fo" -> 4;
            case "fi" -> 5;
            case "si" -> 6;
            case "se" -> 7;
            case "ei" -> 8;
            case "ni" -> 9;
            default -> throw new RuntimeException();
        };
    }

    public static int convertE(String word, int end) {
        if (Character.isDigit(word.charAt(end))) {
            return word.charAt(end) - '0';
        }
        return switch (word.substring(end - 2, end + 1)) {
            case "one" -> 1;
            case "two" -> 2;
            case "ree" -> 3;
            case "our" -> 4;
            case "ive" -> 5;
            case "six" -> 6;
            case "ven" -> 7;
            case "ght" -> 8;
            case "ine" -> 9;
            default -> 0;
        };
    }
}
