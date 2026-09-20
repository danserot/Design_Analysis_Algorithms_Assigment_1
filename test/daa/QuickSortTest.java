package daa;

import daa.algorithms.QuickSort;
import daa.metrics.Metrics;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    private final QuickSort quickSort =
            new QuickSort();

    @Test
    void emptyArray() {

        int[] array = {};

        quickSort.sort(
                array,
                new Metrics()
        );

        assertArrayEquals(
                new int[]{},
                array
        );
    }

    @Test
    void oneElement() {

        int[] array = {10};

        quickSort.sort(
                array,
                new Metrics()
        );

        assertArrayEquals(
                new int[]{10},
                array
        );
    }

    @Test
    void allEqual() {

        int[] array =
                new int[1000];

        Arrays.fill(
                array,
                5
        );

        quickSort.sort(
                array,
                new Metrics()
        );

        for (int value : array) {
            assertEquals(5, value);
        }
    }

    @Test
    void alreadySorted() {

        int[] array =
                new int[1000];

        for (int i = 0;
             i < array.length;
             i++) {

            array[i] = i;
        }

        int[] expected =
                array.clone();

        quickSort.sort(
                array,
                new Metrics()
        );

        assertArrayEquals(
                expected,
                array
        );
    }

    @Test
    void randomArrays() {

        Random random =
                new Random(42);

        for (int test = 0;
             test < 100;
             test++) {

            int size =
                    random.nextInt(1000);

            int[] actual =
                    new int[size];

            for (int i = 0;
                 i < size;
                 i++) {

                actual[i] =
                        random.nextInt();
            }

            int[] expected =
                    actual.clone();

            Arrays.sort(expected);

            quickSort.sort(
                    actual,
                    new Metrics()
            );

            assertArrayEquals(
                    expected,
                    actual
            );
        }
    }

    @Test
    void recursionDepthShouldBeBounded() {

        int n = 100_000;

        int[] array =
                new int[n];

        for (int i = 0;
             i < n;
             i++) {

            array[i] = i;
        }

        Metrics metrics =
                new Metrics();

        quickSort.sort(
                array,
                metrics
        );

        double log2 =
                Math.log(n)
                        / Math.log(2);

        double maximumAllowedDepth =
                2 * log2;

        assertTrue(
                metrics.getMaxDepth()
                        <= maximumAllowedDepth,
                "Depth was "
                        + metrics.getMaxDepth()
                        + ", maximum allowed is "
                        + maximumAllowedDepth
        );
    }
}