package oo.hide;

public class Fibonacci {

    int[] lastTwoNumbers = new int[2];

    public int nextValue() {
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

}
