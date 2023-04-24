package junit.sales;

public class DynamicArrayTests {

    public static void main(String[] args) {

        DynamicArray<String> test = new DynamicArray<>();
        test.add("hi");
        test.add("lol");
        test.add("xd");
        test.add("lmao");
        test.insert(2, "center");


        System.out.println('\0');
        System.out.println("\u9998");
        System.out.println(Character.MAX_VALUE);
        System.out.println(Character.toChars(65535));
        System.out.println(Character.toChars(123));
        System.out.println(Integer.toHexString(123));
        System.out.println('\u007b');
        System.out.println('{' + 0);

        for (int i = 0; i < 65535; i++) {
            System.out.println(Character.toChars(i));
        }
    }

}
