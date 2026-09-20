package daa;

import daa.algorithms.QuickSelect;
import daa.metrics.Metrics;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSelectTest {

    private final QuickSelect quickSelect =
            new QuickSelect();

    @Test
    void findsCorrectElements() {

        int[] array = {
                9, 2, 7, 1, 5
        };

        assertEquals(
                1,
                quickSelect.select(
                        array.clone(),
                        0
                )
        );

        assertEquals(
                5,
                quickSelect.select(
                        array.clone(),
                        2
                )
        );

        assertEquals(
                9,
                quickSelect.select(
                        array.clone(),
                        4
                )
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
                    random.nextInt(999) + 1;

            int[] array =
                    new int[size];

            for (int i = 0;
                 i < size;
                 i++) {

                array[i] =
                        random.nextInt();
            }

            int[] sorted =
                    array.clone();

            Arrays.sort(sorted);

            int k =
                    random.nextInt(size);

            int result =
                    quickSelect.select(
                            array.clone(),
                            k,
                            new Metrics()
                    );

            assertEquals(
                    sorted[k],
                    result
            );
        }
    }

    @Test
    void allEqual() {

        int[] array =
                new int[1000];

        Arrays.fill(
                array,
                42
        );

        assertEquals(
                42,
                quickSelect.select(
                        array,
                        500
                )
        );
    }

    @Test
    void emptyArrayThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quickSelect.select(
                        new int[]{},
                        0
                )
        );
    }

    @Test
    void negativeKThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quickSelect.select(
                        new int[]{1, 2, 3},
                        -1
                )
        );
    }

    @Test
    void tooLargeKThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> quickSelect.select(
                        new int[]{1, 2, 3},
                        3
                )
        );
    }
}