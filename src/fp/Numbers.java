package fp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    private List<Integer> numbers = Arrays.asList(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {
        List<Integer> oddNumbers = numbers.stream()
                .filter(integer -> integer % 2 == 1)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {
        List<Integer> oddNumbersOver10 = numbers.stream()
                .filter(integer -> integer % 2 == 1 && integer > 10)
                .toList();
    }

    @Test
    public void findsSquaredOddNumbers() {
        List<Integer> oddNumbersSquared = numbers.stream()
                .filter(integer -> integer % 2 == 1)
                .map(integer -> integer * integer)
                .toList();
    }

    @Test
    public void findsSumOfOddNumbers() {
        int oddNumbersSum = numbers.stream()
                .filter(integer -> integer % 2 == 1)
                .mapToInt(Integer::intValue)
                .sum();
    }

}
