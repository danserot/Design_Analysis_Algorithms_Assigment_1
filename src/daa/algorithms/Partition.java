package daa.algorithms;

import daa.metrics.Metrics;

import java.util.concurrent.ThreadLocalRandom;

public final class Partition {

    private Partition() {
    }

    public static int[] partition(
            int[] a,
            int left,
            int right,
            Metrics metrics
    ) {

        int pivotIndex = ThreadLocalRandom.current()
                .nextInt(left, right + 1);

        int pivot = a[pivotIndex];

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {

            metrics.incrementComparisons();

            if (a[current] < pivot) {
                swap(a, less, current);

                less++;
                current++;

            } else {

                metrics.incrementComparisons();

                if (a[current] > pivot) {
                    swap(a, current, greater);

                    greater--;
                } else {
                    current++;
                }
            }
        }

        // [left, less - 1]      < pivot
        // [less, greater]       = pivot
        // [greater + 1, right]  > pivot

        return new int[]{less, greater};
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}