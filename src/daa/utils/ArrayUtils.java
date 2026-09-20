package daa.utils;

import java.util.Arrays;
import java.util.Random;

public final class ArrayUtils {

    private static final Random RANDOM = new Random();

    private ArrayUtils() {
    }

    public static int[] generate(
            int size,
            InputType type
    ) {

        return switch (type) {

            case RANDOM ->
                    randomArray(size);

            case SORTED ->
                    sortedArray(size);

            case DUPLICATES ->
                    duplicatesArray(size);
        };
    }

    public static int[] randomArray(int size) {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }

        return array;
    }

    public static int[] sortedArray(int size) {

        int[] array = randomArray(size);

        Arrays.sort(array);

        return array;
    }

    public static int[] duplicatesArray(int size) {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(10);
        }

        return array;
    }

    public static int[] copy(int[] array) {
        return Arrays.copyOf(array, array.length);
    }
}