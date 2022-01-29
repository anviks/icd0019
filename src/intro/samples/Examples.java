package intro.samples;

public class Examples {

    public static void main(String[] args) {

        // print to console
        System.out.println("Hello!"); // Hello!

        // simple while loop
        int i = 0;
        while (i < 3) {
            System.out.println(i);
            i++;
        }

        // loop from 0 to 2
        for (int j = 0; j < 3; j++) {
            System.out.println(j); // 0 ...
        }

        // concatenate two strings
        // NB! double quotes
        String concatenated = "aa" + "bb";
        System.out.println(concatenated); // aabb

        // get single letter (character) from string
        System.out.println(concatenated.charAt(2)); // b

        // get string length
        System.out.println(concatenated.length()); // 4

        // compare two letters
        // NB! Letters are in single quotes.
        // NB! Letter is different from one letter string ("a" != 'a')
        if (concatenated.charAt(2) == 'b') {
            System.out.println(true); // true
        }

        // use modulo operator to check whether number is even or odd
        if (4 % 2 == 0) {
            System.out.println("even");
        }
        if (5 % 2 == 1) {
            System.out.println("odd");
        }

    }
}
