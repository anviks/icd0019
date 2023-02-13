package junit.sales;

public class DynamicArrayTests {

    public static void main(String[] args) {

        DynamicArray<String> test = new DynamicArray<>();
        test.add("hi");
        test.add("lol");
        test.add("xd");
        test.add("lmao");
        test.insert(2, "center");

    }

}
