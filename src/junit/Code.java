package junit;

import java.util.Arrays;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString) {

        if ("".equals(inputString)) {
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

        char[] letters = inputString.toCharArray();
        int maxCount = 0;
        char maxChar = '\0';

        for (char letter : letters) {
            int count = getCharacterCount(inputString, letter);
            if (count > maxCount
                    || count == maxCount && inputString.indexOf(letter) < inputString.indexOf(maxChar)) {
                maxCount = count;
                maxChar = letter;
            }
        }

        return maxChar == '\0' ? null : maxChar;
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
        int[] distinctIntegers = new int[integers.length];
        int i = 0;

        for (int j = 0; j < integers.length; j++) {
            if (!contains(distinctIntegers, integers[j], j)) {
                distinctIntegers[i++] = integers[j];
            }
        }

        int[] shortenedIntegers = new int[i];

        for (int j = 0; j < shortenedIntegers.length; j++) {
            shortenedIntegers[j] = distinctIntegers[j];
        }

        return shortenedIntegers;
    }

    public static boolean contains(int[] ints, int element, int index) {
        for (int i = 0; i < index; i++) {
            if (ints[i] == element) {
                return true;
            }
        }

        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        return Arrays.stream(removeDuplicates(integers)).sum();
    }

}
