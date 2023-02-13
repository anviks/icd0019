package junit.sales;

import java.util.ArrayList;
import java.util.Arrays;

public class DynamicArrayTests {

    public static void main(String[] args) {

        DynamicArray<String> test = new DynamicArray<>();
        test.add("hi");
        test.add("lol");
        test.add("xd");
        test.add("lmao");
        test.insert(2, "center");

        ArrayList<String> o = new ArrayList<>();
        o.add("lol");
        o.add("xd");
        System.out.println(o);



    }

}
