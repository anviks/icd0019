package exceptions.validate;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class MinimumElementTests {

    @Test
    public void findsMinimumFromArrayOfNumbers() {
        assertThat(MinimumElement.minimumElement(new int[] { 1, 3 }), is(1));
        assertThat(MinimumElement.minimumElement(new int[] { 1, 0 }), is(0));
    }

    @Test
    public void emptyArrayThrowsIllegalArgumentException() {
        try {
            int minValue = MinimumElement.minimumElement(new int[0]);
            assertEquals("Should throw IllegalArgumentException", "");
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void nullThrowsIllegalArgumentException() {
        try {
            int minValue = MinimumElement.minimumElement(null);
            assertEquals("Should throw IllegalArgumentException", "");
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

}
