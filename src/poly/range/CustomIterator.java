package poly.range;

import java.util.Iterator;

public class CustomIterator implements Iterator<Integer> {

    private int current;
    private final int end;

    public CustomIterator(int start, int end) {
        this.current = start;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return current < end;
    }

    @Override
    public Integer next() {
        return hasNext() ? current++ : null;
    }
}
