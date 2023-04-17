package poly.range;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CustomIterable implements Iterable<Integer> {

    private final int start;
    private final int end;

    public CustomIterable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator(start, end);
    }
}
