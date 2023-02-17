package oo.hide;

import java.util.ArrayList;

public class Fibonacci {

    ArrayList<Integer> numbers = new ArrayList<>();

    public int nextValue() {

        switch (numbers.size()) {
            case 0 -> numbers.add(0);
            case 1 -> numbers.add(1);
            default -> {
                int last = numbers.get(numbers.size() - 1);
                int secondLast = numbers.get(numbers.size() - 2);
                numbers.add(last + secondLast);
            }
        }

        return numbers.get(numbers.size() - 1);
    }

}
