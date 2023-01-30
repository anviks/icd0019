package intro;

public class Program {

    public static void main(String[] args) {

        int decimal = asDecimal("11001101");

        System.out.println(decimal); // 205
        System.out.println(asString(205));
        System.out.println(pow(2, 5));
        System.out.println(pow(4, 2));
        System.out.println(pow(4, 0));

    }

    public static String asString(int input) {
        return Integer.toBinaryString(input);
    }

    public static int asDecimal(String input) {
        return Integer.parseInt(input, 2);
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.

        int result = 1;

        for (int i = 0; i < power; i++) {
            result *= arg;
        }

        return result;
    }

}
