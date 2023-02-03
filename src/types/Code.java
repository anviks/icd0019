package types;

import java.util.*;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println(sum(numbers)); // 11
        System.out.println(average(numbers));
        System.out.println(asString(numbers));
        System.out.println(mode("ogjdsjaoihsdo"));
        System.out.println(squareDigits("a9b2"));
        System.out.println(isolatedSquareCount());
    }

    public static int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public static double average(int[] numbers) {
        return Double.valueOf(sum(numbers)) / numbers.length;
    }

    public static Integer minimumElement(int[] integers) {
        OptionalInt minValue = Arrays.stream(integers).min();

        return minValue.isPresent() ? minValue.getAsInt() : null;
    }

    public static String asString(int[] elements) {
        return Arrays.toString(elements).replace("[", "").replace("]", "");
    }

    public static Character mode(String input) {

        HashMap<Character, Integer> occurrences = new HashMap<>();
        char[] letters = input.toCharArray();

        for (char letter : letters) {
            occurrences.putIfAbsent(letter, 0);
            occurrences.put(letter, occurrences.get(letter) + 1);
        }

        Optional<Character> max = occurrences.keySet().stream().max(Comparator.comparingInt(occurrences::get));

        return max.orElse(null);
    }

    public static String squareDigits(String s) {
        StringBuilder result = new StringBuilder();

        for (char letter : s.toCharArray()) {
            if (Character.isDigit(letter)) {
                int number = Integer.parseInt(letter + "");
                result.append(number * number);
            } else {
                result.append(letter);
            }
        }

        return result.toString();
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        int isolatedCount = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                boolean isolated = isIsolated(matrix, row, column, true);

                if (isolated) {
                    isolatedCount++;
                }
            }
        }

        return isolatedCount;
    }

    private static boolean isIsolated(boolean[][] matrix, int row, int column, boolean isolated) {
        try {
            isolated &= !matrix[row - 1][column - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row - 1][column];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row - 1][column + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row + 1][column - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row + 1][column];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row + 1][column + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row][column - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            isolated &= !matrix[row][column + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return isolated;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
