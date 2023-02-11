package junit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString) {

        if (inputString.equals("")) {
            return 0;
        }

        int longest = 1;
        int cache = 1;

        for (int i = 1; i < inputString.length(); i++) {

            if (inputString.charAt(i) == inputString.charAt(i - 1)) {
                cache++;
            } else {
                if (cache > longest) {
                    longest = cache;
                }
                cache = 1;
            }
        }

        return Math.max(longest, cache);
    }

    public static Character mode(String inputString) {
        if (inputString == null) {
            return null;
        }

        HashMap<Character, Integer> occurrences = new HashMap<>();
        char[] letters = inputString.toCharArray();

        for (char letter : letters) {
            occurrences.put(letter, getCharacterCount(inputString, letter));
        }

        Optional<Character> max = occurrences
                .keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .max(Comparator.comparingInt(occurrences::get));

        return max.orElse(null);
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int count = 0;

        for (char character : allCharacters.toCharArray()) {
            if (character == targetCharacter) {
                count++;
            }
        }

        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        return Arrays.stream(integers).distinct().toArray();
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        return Arrays.stream(removeDuplicates(integers)).sum();
    }

}
