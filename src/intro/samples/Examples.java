package intro.samples;

public class Examples {

    public static void main(String[] args) {

        // print to console
        System.out.println("Hello!"); // Hello!

        // loop from 0 to 2
        for (int i = 0; i < 3; i++) {
            System.out.println(i); // 0 ...
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
    }
}
