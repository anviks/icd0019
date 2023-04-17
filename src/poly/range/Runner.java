package poly.range;

import org.junit.Test;

import java.util.Iterator;

public class Runner {

    @Test
    public void canIterateRange() {
        for (Integer integer : range(1, 7)) {
            System.out.println(integer);
        }
    }

    private Iterable<Integer> range(int start, int end) {
        return new CustomIterable(start, end);
    }


}
