import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class MainTest {

    @Test
    public void testBubble() throws Exception{
        final int[] numbers = {9, 6, 5, 2};
        final int[] expected = {2, 5, 6, 9};

        Main.mergeSort(numbers);
        assertArrayEquals(expected, numbers);
    }

    @Test
    public void testBinarySearch() throws Exception{
        final int [] A = {1,2, 3, 5, 6, 7, 9 , 12, 24};
        assertEquals(7, Main.optimizedBinarySearch(A, 0, A.length, 12));
    }
}