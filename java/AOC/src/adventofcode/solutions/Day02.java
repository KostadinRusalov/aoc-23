package adventofcode.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day02 {
    public static final int RED_CUBES = 12;
    public static final int GREEN_CUBES = 13;
    public static final int BLUE_CUBES = 14;

    public record CubeSet(int red, int green, int blue) {
        public static CubeSet of(String cubeSet) {
            String[] set = cubeSet.split(", ");
            int red = 0;
            int green = 0;
            int blue = 0;

            for (String cubes : set) {
                String[] tokens = cubes.split(" ");
                switch (tokens[1]) {
                    case "red" -> red = Integer.parseInt(tokens[0]);
                    case "green" -> green = Integer.parseInt(tokens[0]);
                    case "blue" -> blue = Integer.parseInt(tokens[0]);
                }
            }
            return new CubeSet(red, green, blue);
        }

        public boolean isPossible() {
            return red <= RED_CUBES && green <= GREEN_CUBES && blue <= BLUE_CUBES;
        }
    }

    public record Game(int id, List<CubeSet> cubes) {
        public static Game of(String game) {
            String[] tokens = game.split(": ");
            int id = Integer.parseInt(tokens[0].split(" ")[1]);
            List<CubeSet> cubes = Arrays.stream(tokens[1].split("; ")).map(CubeSet::of).toList();

            return new Game(id, cubes);
        }

        public boolean isPossible() {
            return cubes.stream().allMatch(CubeSet::isPossible);
        }

        public int getPower() {
            int red = 1;
            int green = 1;
            int blue = 1;

            for (CubeSet set : cubes) {
                red = Math.max(red, set.red);
                green = Math.max(green, set.green);
                blue = Math.max(blue, set.blue);
            }

            return red * green * blue;
        }
    }

    public static int sumOfPossible(String input) {
        return Pattern.compile("\\n")
            .splitAsStream(input)
            .map(Game::of)
            .filter(Game::isPossible)
            .mapToInt(Game::id)
            .sum();
    }

    public static int sumOfPowers(String input) {
        return Arrays.stream(input.split("\\n"))
            .map(Game::of)
            .mapToInt(Game::getPower)
            .sum();
    }
}
