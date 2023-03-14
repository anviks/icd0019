package collections.cache;

public class Fibonacci {
    int[] lastTwoNumbers = new int[2];

    private int nextValue() {
        if (lastTwoNumbers[1] == 0) {
            lastTwoNumbers[1] = 1;
        } else {
            int first = lastTwoNumbers[1];
            int second = lastTwoNumbers[0] + lastTwoNumbers[1];
            lastTwoNumbers[0] = first;
            lastTwoNumbers[1] = second;
        }

        return lastTwoNumbers[0];
    }

    public Integer fib(Integer n) {
        for (int i = 0; i < n; i++) {
            nextValue();
        }

        return lastTwoNumbers[1];
    }

}
