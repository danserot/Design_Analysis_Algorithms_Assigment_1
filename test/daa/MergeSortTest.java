package daa;

import daa.algorithms.MergeSort;
import daa.metrics.Metrics;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    private final MergeSort mergeSort =
            new MergeSort();

    @Test
    void emptyArray() {

        int[] array = {};

        mergeSort.sort(
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

        int[] array = {5};

        mergeSort.sort(
                array,
                new Metrics()
        );

        assertArrayEquals(
                new int[]{5},
                array
        );
    }

    @Test
    void allEqual() {

        int[] array = {
                7, 7, 7, 7, 7
        };

        int[] expected =
                array.clone();

        Arrays.sort(expected);

        mergeSort.sort(
                array,
                new Metrics()
        );

        assertArrayEquals(
                expected,
                array
        );
    }

    @Test
    void alreadySorted() {

        int[] array = {
                1, 2, 3, 4, 5
        };

        int[] expected =
                array.clone();

        mergeSort.sort(
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

            mergeSort.sort(
                    actual,
                    new Metrics()
            );

            assertArrayEquals(
                    expected,
                    actual
            );
        }
    }
}