package oo.hide;

public class Fibonacci {

    DynamicArray<Integer> numbers = new DynamicArray<>();

    public int nextValue() {

        switch (numbers.length()) {
            case 0 -> numbers.add(0);
            case 1 -> numbers.add(1);
            default -> {
                int last = numbers.get(numbers.length() - 1);
                int secondLast = numbers.get(numbers.length() - 2);
                numbers.add(last + secondLast);
            }
        }

        return numbers.get(numbers.length() - 1);
    }

}
