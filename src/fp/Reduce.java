package fp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Reduce {

    @Test
    public void reducesListToSingleResult() {

        var numbers = List.of(1, 2, 3, 4);

        assertThat(reduce(numbers, (a, b) -> a + b), is(10));

        assertThat(reduce(numbers, (a, b) -> a * b), is(24));

        assertThat(reduce(List.of("1", "2", "3"),
                (a, b) -> a + b), is("123"));

    }

    private <T> T reduce(List<T> list,
                        BiFunction<T, T, T> func) {
        if (list.isEmpty()) {
            return null;
        }

        List<T> elements = new ArrayList<>(list);
        T result = elements.remove(0);

        for (T element : elements) {
            result = func.apply(result, element);
        }

        return result;
    }
}
